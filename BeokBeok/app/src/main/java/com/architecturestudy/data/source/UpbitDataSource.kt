package com.architecturestudy.data.source

import com.architecturestudy.data.UpbitTicker
import io.reactivex.disposables.Disposable

interface UpbitDataSource {
    interface Local {
        suspend fun saveTicker(upbitTicker: UpbitTicker)

        suspend fun sort(
            sortType: String,
            isDesc: Boolean,
            onSuccess: (List<UpbitTicker>) -> Unit,
            onFail: (Throwable) -> Unit
        )
    }

    interface Remote {
        fun getMarketPrice(
            prefix: String,
            onSuccess: (List<UpbitTicker>) -> Unit,
            onFail: (Throwable) -> Unit
        ): Disposable
    }

}