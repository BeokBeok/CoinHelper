package com.architecturestudy.data.source

import com.architecturestudy.data.UpbitTicker
import io.reactivex.disposables.Disposable

class UpbitRepository(
    private val upbitLocalDataSource: UpbitDataSource.Local,
    private val upbitRemoteDataSource: UpbitDataSource.Remote
) : UpbitDataSource.Local,
    UpbitDataSource.Remote {

    override fun getMarketPrice(
        prefix: String,
        onSuccess: (List<UpbitTicker>) -> Unit,
        onFail: (Throwable) -> Unit
    ): Disposable = upbitRemoteDataSource.getMarketPrice(
        prefix,
        onSuccess,
        onFail
    )

    override fun saveTicker(upbitTicker: UpbitTicker): Disposable? =
        upbitLocalDataSource.saveTicker(upbitTicker)

    override fun sort(
        sortType: String,
        isDesc: Boolean,
        onSuccess: (List<UpbitTicker>) -> Unit,
        onFail: (Throwable) -> Unit
    ): Disposable? = upbitLocalDataSource.sort(
        sortType,
        isDesc,
        onSuccess,
        onFail
    )
}