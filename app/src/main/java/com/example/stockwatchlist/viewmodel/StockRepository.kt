package com.example.stockwatchlist.viewmodel

import android.util.Log
import com.example.stockwatchlist.api.AlphaVantageApi
import com.example.stockwatchlist.api.Stock

class StockRepository(private val apiService: AlphaVantageApi) {

    suspend fun getTimeSeriesDaily(symbol: String): List<Stock> {
        try {
            val response = apiService.getTimeSeriesDaily(symbol = symbol)
            Log.d("StockRepository", "API Response: $response")

            if (response.metaData == null || response.timeSeries == null) {
                Log.e("StockRepository", "Meta Data or Time Series is null in the response")
                if (response.metaData == null && response.timeSeries == null) {
                    throw Exception("API rate limit reached or invalid API key. Please check your usage or upgrade to a premium plan.")
                }
                return emptyList()
            }

            val timeSeries = response.timeSeries
            Log.d("StockRepository", "Received ${timeSeries.size} daily data points")

            return timeSeries.map { (date, dailyData) ->
                Stock(
                    symbol = symbol,
                    name = response.metaData.symbol,
                    type = "Equity",
                    currency = "USD",
                    price = dailyData.close
                )
            }
        } catch (e: Exception) {
            Log.e("StockRepository", "Error fetching daily time series: ${e.message}")
            return emptyList()
        }
    }
}


