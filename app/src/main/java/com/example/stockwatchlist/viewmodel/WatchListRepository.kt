package com.example.stockwatchlist.viewmodel

import com.example.stockwatchlist.data.WatchListStockDao
import com.example.stockwatchlist.data.WatchListStocks

class WatchListRepository(private val stockDao: WatchListStockDao) {
    val allStocks = stockDao.getAllStocks()

    suspend fun insertStock(stock: WatchListStocks) {
        stockDao.insertStock(stock)
    }

    suspend fun deleteStock(stock: WatchListStocks) {
        stockDao.deleteStock(stock)
    }
}
