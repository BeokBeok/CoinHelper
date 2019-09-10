package com.architecturestudy.data.source

import com.architecturestudy.data.UpbitTicker

class UpbitRepository(
    private val upbitLocalDataSource: UpbitDataSource.Local,
    private val upbitRemoteDataSource: UpbitDataSource.Remote
) : UpbitDataSource.Local,
    UpbitDataSource.Remote {

    override suspend fun saveTicker(upbitTicker: UpbitTicker) =
        upbitLocalDataSource.saveTicker(upbitTicker)

    override suspend fun sort(
        sortType: String,
        isDesc: Boolean,
        onSuccess: (List<UpbitTicker>) -> Unit,
        onFail: (Throwable) -> Unit
    ) = upbitLocalDataSource.sort(
        sortType,
        isDesc,
        onSuccess,
        onFail
    )

    override suspend fun getMarketPrice(
        prefix: String,
        onSuccess: (List<UpbitTicker>) -> Unit,
        onFail: (Throwable) -> Unit
    ) = upbitRemoteDataSource.getMarketPrice(
        prefix,
        onSuccess,
        onFail
    )
}