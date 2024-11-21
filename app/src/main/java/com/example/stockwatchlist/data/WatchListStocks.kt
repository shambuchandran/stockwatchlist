package com.example.stockwatchlist.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "watchlist_table")
data class WatchListStocks(
    @PrimaryKey val symbol: String,
    val name: String,
    val type: String,
    val currency: String,
    val price: String
)
