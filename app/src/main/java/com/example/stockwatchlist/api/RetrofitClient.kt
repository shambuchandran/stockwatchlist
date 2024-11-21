package com.example.stockwatchlist.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//object RetrofitClient {
//    private const val BASE_URL = "https://www.alphavantage.co/"
//    val api: StockApiService by lazy {
//        Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(StockApiService::class.java)
//    }
//}

object RetrofitClient {
    private const val BASE_URL = "https://www.alphavantage.co/"

    private val interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    val api: AlphaVantageApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(AlphaVantageApi::class.java)
    }
}
