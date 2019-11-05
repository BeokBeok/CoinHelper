package com.architecturestudy.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.architecturestudy.data.UpbitTicker

@Dao
interface UpbitTickerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTicker(ticker: UpbitTicker)

    @Query("SELECT * FROM upbitTicker ORDER BY market")
    suspend fun sortMarket(): List<UpbitTicker>

    @Query("SELECT * FROM upbitTicker ORDER BY market DESC")
    suspend fun sortMarketByDESC(): List<UpbitTicker>

    @Query("SELECT * FROM upbitticker ORDER BY trade_price")
    suspend fun sortTradePrice(): List<UpbitTicker>

    @Query("SELECT * FROM upbitTicker ORDER BY trade_price DESC")
    suspend fun sortTradePriceByDESC(): List<UpbitTicker>

    @Query("SELECT * FROM upbitTicker ORDER BY signed_change_rate")
    suspend fun sortSignedChangeRate(): List<UpbitTicker>

    @Query("SELECT * FROM upbitTicker ORDER BY signed_change_rate DESC")
    suspend fun sortSignedChangeRateByDESC(): List<UpbitTicker>

    @Query("SELECT * FROM upbitTicker ORDER BY acc_trade_price_24h")
    suspend fun sortAccTradePrice24h(): List<UpbitTicker>

    @Query("SELECT * FROM upbitTicker ORDER BY acc_trade_price_24h DESC")
    suspend fun sortAccTradePrice24hByDESC(): List<UpbitTicker>

}