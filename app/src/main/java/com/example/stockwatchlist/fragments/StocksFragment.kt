package com.example.stockwatchlist.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.withStateAtLeast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stockwatchlist.adapter.StockAdapter
import com.example.stockwatchlist.api.RetrofitClient
import com.example.stockwatchlist.data.WatchListStockDatabase
import com.example.stockwatchlist.data.WatchListStocks
import com.example.stockwatchlist.databinding.FragmentStocksBinding
import com.example.stockwatchlist.viewmodel.StockRepository
import com.example.stockwatchlist.viewmodel.StockViewModel
import com.example.stockwatchlist.viewmodel.StockViewModelFactory
import com.example.stockwatchlist.viewmodel.WatchListRepository
import com.example.stockwatchlist.viewmodel.WatchListViewModel
import com.example.stockwatchlist.viewmodel.WatchListViewModelFactory

class StocksFragment : Fragment() {
    private lateinit var stockViewModel: StockViewModel
    private lateinit var watchListViewModel: WatchListViewModel
    private lateinit var stockAdapter: StockAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var searchView: SearchView
    private lateinit var searchIcon: ImageView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentStocksBinding.inflate(inflater, container, false)
        val database = WatchListStockDatabase.getInstance(requireContext())
        val watchListRepository = WatchListRepository(database.watchListDao())

        watchListViewModel = ViewModelProvider(
            this,
            WatchListViewModelFactory(watchListRepository)
        )[WatchListViewModel::class.java]
        stockAdapter = StockAdapter(emptyList(),watchListViewModel)

        binding.stockRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = stockAdapter
        }
        progressBar = binding.progressBar
        searchView = binding.searchView
        searchIcon = binding.searchIcon

        val repository = StockRepository(RetrofitClient.api)
        val viewModelFactory = StockViewModelFactory(repository)
        stockViewModel = ViewModelProvider(this, viewModelFactory)[StockViewModel::class.java]

        //loadInitialStocks()
        stockViewModel.stocks.observe(viewLifecycleOwner, Observer {
            Log.d("stocks fragment",it.toString())
            stockAdapter.updateStocks(it)
        })
        stockViewModel.isLoading.observe(viewLifecycleOwner, Observer { isLoading ->
            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        })
        stockViewModel.error.observe(viewLifecycleOwner, Observer { error ->
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
        })

        searchIcon.setOnClickListener {
            searchView.visibility = if (searchView.visibility == View.GONE) View.VISIBLE else View.GONE
        }
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { stockViewModel.getTimeSeriesDaily(it)
                    searchView.clearFocus()
                }
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                //newText?.let { stockViewModel.updateSearchQuery(it) }
                return true
            }
        })
        return binding.root
    }
    private fun loadInitialStocks() {
        val top20Symbols = listOf( "AAPL", "MSFT", "AMZN", "GOOGL", "TSLA",
            "BRK.B", "META", "NVDA", "JNJ", "PG", "UNH", "V", "MA", "KO",
            "PFE", "HD", "MRK", "VZ", "CVX", "XOM" )
        top20Symbols.forEach { symbol -> stockViewModel.getTimeSeriesDaily(symbol)
        }
    }
}
