package com.example.stockwatchlist.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [WatchListStocks::class], version = 1, exportSchema = false)
abstract class WatchListStockDatabase:RoomDatabase() {
    abstract fun watchListDao():WatchListStockDao
    companion object {
        @Volatile private var INSTANCE: WatchListStockDatabase? = null

        fun getInstance(context: Context): WatchListStockDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WatchListStockDatabase::class.java,
                    "watchlist_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}