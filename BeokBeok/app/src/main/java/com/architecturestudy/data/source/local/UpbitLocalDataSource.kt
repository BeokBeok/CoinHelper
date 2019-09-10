package com.architecturestudy.data.source.local

import com.architecturestudy.data.UpbitTicker
import com.architecturestudy.data.source.UpbitDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UpbitLocalDataSource(
    private val upbitTickerDao: UpbitTickerDao
) : UpbitDataSource.Local {

    override suspend fun saveTicker(upbitTicker: UpbitTicker) = withContext(Dispatchers.IO) {
        upbitTickerDao.insertTicker(upbitTicker)
    }

    override suspend fun sort(
        sortType: String,
        isDesc: Boolean,
        onSuccess: (List<UpbitTicker>) -> Unit,
        onFail: (Throwable) -> Unit
    ) = withContext(Dispatchers.IO) {
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
        withContext(Dispatchers.Main) {
            if (sortedList.isNullOrEmpty()) onFail(IllegalStateException("Sort fail"))
            else onSuccess(sortedList)
        }
    }
}