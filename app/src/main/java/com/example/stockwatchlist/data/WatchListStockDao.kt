package com.example.stockwatchlist.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WatchListStockDao {
    @Query("SELECT * FROM watchlist_table")
    fun getAllStocks(): LiveData<List<WatchListStocks>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStock(stock: WatchListStocks): Long

    @Delete
    suspend fun deleteStock(stock: WatchListStocks):Int
}