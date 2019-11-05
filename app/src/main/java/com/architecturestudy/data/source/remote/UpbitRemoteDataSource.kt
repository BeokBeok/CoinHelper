package com.architecturestudy.data.source.remote

import com.architecturestudy.common.MarketTypes
import com.architecturestudy.data.UpbitTicker
import com.architecturestudy.data.source.UpbitDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class UpbitRemoteDataSource(
    private val retrofit: UpbitRemoteService
) : UpbitDataSource.Remote {

    override suspend fun getMarketPrice(
        prefix: String,
        onSuccess: (List<UpbitTicker>) -> Unit,
        onFail: (Throwable) -> Unit
    ) {
        var tickerList = listOf<UpbitTicker>()
        withContext(Dispatchers.IO) {
            val marketList = retrofit.getMarkets()
            tickerList = try {
                retrofit.getTicker(
                    marketList
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
            } catch (e: HttpException) {
                listOf()
            }
        }
        withContext(Dispatchers.Main) {
            tickerList.run {
                if (isNullOrEmpty()) onFail(IllegalStateException("Data is empty"))
                else onSuccess(this)
            }
        }
    }
}