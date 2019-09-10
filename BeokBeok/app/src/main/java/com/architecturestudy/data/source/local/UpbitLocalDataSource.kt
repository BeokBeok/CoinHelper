package com.architecturestudy.data.source.local

import com.architecturestudy.data.UpbitTicker
import com.architecturestudy.data.source.UpbitDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UpbitLocalDataSource(
    private val upbitTickerDao: UpbitTickerDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : UpbitDataSource.Local {

    override suspend fun saveTicker(upbitTicker: UpbitTicker) = withContext(ioDispatcher) {
        upbitTickerDao.insertTicker(upbitTicker)
    }

    override suspend fun sort(
        sortType: String,
        isDesc: Boolean,
        onSuccess: (List<UpbitTicker>) -> Unit,
        onFail: (Throwable) -> Unit
    ) = withContext(ioDispatcher) {
        val sortedList = when (sortType) {
            "market" -> {
                if (isDesc) upbitTickerDao.sortMarketByDESC()
                else upbitTickerDao.sortMarket()
            }
            "trade_price" -> {
                if (isDesc) upbitTickerDao.sortTradePriceByDESC()
                else upbitTickerDao.sortTradePrice()
            }
            "signed_change_rate" -> {
                if (isDesc) upbitTickerDao.sortSignedChangeRateByDESC()
                else upbitTickerDao.sortSignedChangeRate()
            }
            "acc_trade_price_24h" -> {
                if (isDesc) upbitTickerDao.sortAccTradePrice24hByDESC()
                else upbitTickerDao.sortAccTradePrice24h()
            }
            else -> listOf()
        }
        if (sortedList.isNullOrEmpty()) onFail(IllegalStateException("Sort fail"))
        else onSuccess(sortedList)
    }
}