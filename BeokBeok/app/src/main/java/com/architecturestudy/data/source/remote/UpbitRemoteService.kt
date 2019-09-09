package com.architecturestudy.data.source.remote

import com.architecturestudy.data.UpbitTicker
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface UpbitRemoteService {
    @GET("v1/market/all")
    fun getMarkets(): Single<List<UpbitTicker>>

    @GET("v1/ticker")
    fun getTicker(
        @Query("markets")
        list: List<String?>?
    ): Single<List<UpbitTicker>>
}