package com.architecturestudy.data.source

import com.architecturestudy.data.UpbitTicker

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
        suspend fun getMarketPrice(
            prefix: String,
            onSuccess: (List<UpbitTicker>) -> Unit,
            onFail: (Throwable) -> Unit
        )
    }

}