package com.architecturestudy.data.source.remote

import com.architecturestudy.data.UpbitTicker
import retrofit2.http.GET
import retrofit2.http.Query

interface UpbitRemoteService {
    @GET("v1/market/all")
    suspend fun getMarkets(): List<UpbitTicker>

    @GET("v1/ticker")
    suspend fun getTicker(
        @Query("markets")
        list: List<String?>?
    ): List<UpbitTicker>
}