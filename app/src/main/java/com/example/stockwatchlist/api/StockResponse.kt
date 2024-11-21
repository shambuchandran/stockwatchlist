package com.example.stockwatchlist.api

import com.google.gson.annotations.SerializedName



data class TimeSeriesDailyResponse(
    @SerializedName("Meta Data")
    val metaData: MetaData,
    @SerializedName("Time Series (Daily)")
    val timeSeries: Map<String, DailyData>
)

data class MetaData(
    @SerializedName("1. Information") val information: String,
    @SerializedName("2. Symbol") val symbol: String,
    @SerializedName("3. Last Refreshed") val lastRefreshed: String,
    @SerializedName("4. Output Size") val outputSize: String,
    @SerializedName("5. Time Zone") val timeZone: String
)

data class DailyData(
    @SerializedName("1. open") val open: String,
    @SerializedName("2. high") val high: String,
    @SerializedName("3. low") val low: String,
    @SerializedName("4. close") val close: String,
    @SerializedName("5. volume") val volume: String
)

data class Stock(
    val symbol: String,
    val name: String,
    val type: String,
    val currency: String,
    val price: String
)



