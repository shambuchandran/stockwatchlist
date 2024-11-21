package com.example.stockwatchlist.api

import retrofit2.http.GET
import retrofit2.http.Query


interface AlphaVantageApi {
    @GET("query")
    suspend fun getTimeSeriesDaily(
        @Query("function") function: String = "TIME_SERIES_DAILY",
        @Query("symbol") symbol: String,
        @Query("outputsize") outputSize: String = "compact",
        @Query("apikey") apiKey: String = Constant.apiKey
    ): TimeSeriesDailyResponse
}
