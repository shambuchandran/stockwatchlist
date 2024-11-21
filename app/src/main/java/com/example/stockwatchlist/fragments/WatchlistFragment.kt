package com.example.stockwatchlist.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stockwatchlist.adapter.WatchListAdapter
import com.example.stockwatchlist.data.WatchListStockDatabase
import com.example.stockwatchlist.databinding.FragmentWishlistBinding
import com.example.stockwatchlist.viewmodel.WatchListRepository
import com.example.stockwatchlist.viewmodel.WatchListViewModel
import com.example.stockwatchlist.viewmodel.WatchListViewModelFactory

class WatchlistFragment : Fragment() {
    private lateinit var watchListViewModel: WatchListViewModel
    private lateinit var watchListAdapter: WatchListAdapter
    private lateinit var progressBar: ProgressBar
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentWishlistBinding.inflate(inflater, container, false)
        progressBar = binding.progressBar

        val repository =
            WatchListRepository(WatchListStockDatabase.getInstance(requireContext()).watchListDao())
        val viewModelFactory = WatchListViewModelFactory(repository)

        watchListViewModel =
            ViewModelProvider(this, viewModelFactory)[WatchListViewModel::class.java]
        watchListAdapter = WatchListAdapter(emptyList(), watchListViewModel)

        binding.watchlistRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = watchListAdapter
        }

        watchListViewModel.allStocks.observe(viewLifecycleOwner, Observer {
            watchListAdapter.updateStocks(it)
            progressBar.visibility = View.GONE
        })

        return binding.root
    }
}