package com.architecturestudy.data.source.remote

import com.architecturestudy.common.MarketTypes
import com.architecturestudy.data.UpbitTicker
import com.architecturestudy.data.source.UpbitDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class UpbitRemoteDataSource(
    private val retrofit: UpbitRemoteService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : UpbitDataSource.Remote {

    override suspend fun getMarketPrice(
        prefix: String,
        onSuccess: (List<UpbitTicker>) -> Unit,
        onFail: (Throwable) -> Unit
    ) = withContext(ioDispatcher) {
        val marketList = async {
            retrofit.getMarkets()
        }
        val tickerList = async {
            retrofit.getTicker(
                marketList.await()
                    .asSequence()
                    .filter {
                        enumValues<MarketTypes>().any { data ->
                            data.name == prefix
                        }
                    }
                    .filter { data -> data.market.startsWith(prefix) }
                    .map { data -> data.market }
                    .toList()
            )
        }
        withContext(Dispatchers.Main) {
            tickerList.await().run {
                if (isNullOrEmpty()) onFail(IllegalStateException("Data is empty"))
                else onSuccess(this)
            }
        }
    }
}