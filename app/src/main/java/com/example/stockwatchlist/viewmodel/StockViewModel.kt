package com.example.stockwatchlist.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stockwatchlist.api.Stock
import com.example.stockwatchlist.api.StockCache
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class StockViewModel(private val repository: StockRepository) : ViewModel() {
    private val _stocks = MutableLiveData<List<Stock>>()
    val stocks: LiveData<List<Stock>> = _stocks

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val searchQueryFlow = MutableStateFlow("")

    fun getTimeSeriesDaily(symbol: String) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val stockQuotes = repository.getTimeSeriesDaily(symbol)
                if (stockQuotes.isNotEmpty()) {
                    _stocks.value = stockQuotes
                    Log.d("stocks viewmodel",_stocks.value.toString())
                } else {
                    _error.value = "No data found or API rate limit reached"
                }
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun updateSearchQuery(query: String) {
        searchQueryFlow.value = query
    }
}

