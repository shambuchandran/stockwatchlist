package com.example.stockwatchlist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stockwatchlist.data.WatchListStocks
import kotlinx.coroutines.launch

class WatchListViewModel(private val repository: WatchListRepository) : ViewModel() {
    val allStocks : LiveData<List<WatchListStocks>> = repository.allStocks

    fun addStock(stock: WatchListStocks) {
        viewModelScope.launch {
            repository.insertStock(stock)
        }
    }
    fun removeStock(stock: WatchListStocks) {
        viewModelScope.launch {
            repository.deleteStock(stock)
        }
    }
}