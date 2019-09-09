package com.architecturestudy.data.source.remote

import com.architecturestudy.common.MarketTypes
import com.architecturestudy.data.UpbitTicker
import com.architecturestudy.data.source.UpbitDataSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class UpbitRemoteDataSource(
    private val retrofit: UpbitRemoteService
) : UpbitDataSource.Remote {

    override fun getMarketPrice(
        prefix: String,
        onSuccess: (List<UpbitTicker>) -> Unit,
        onFail: (Throwable) -> Unit
    ): Disposable =
        retrofit.getMarkets()
            .subscribeOn(Schedulers.io())
            .flatMap {
                retrofit.getTicker(
                    it.asSequence()
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
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.isNullOrEmpty()) {
                    onFail(IllegalStateException("Data is empty"))
                } else {
                    onSuccess(it)
                }
            }, { onFail(it) })
}