package com.example.stockwatchlist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.stockwatchlist.R
import com.example.stockwatchlist.data.WatchListStocks
import com.example.stockwatchlist.viewmodel.WatchListViewModel

class WatchListAdapter(
    private var stocks: List<WatchListStocks>,
    private val viewModel: WatchListViewModel
) : RecyclerView.Adapter<WatchListAdapter.WatchListViewHolder>() {

    inner class WatchListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val symbolTextView: TextView = itemView.findViewById(R.id.watchlist_stock_symbol)
        private val nameTextView: TextView = itemView.findViewById(R.id.watchlist_stock_name)
        private val typeTextView: TextView = itemView.findViewById(R.id.watchlist_stock_type)
        private val currencyTextView: TextView = itemView.findViewById(R.id.watchlist_stock_currency)
        private val priceTextView: TextView = itemView.findViewById(R.id.watchlist_stock_price)
        private val deleteButton: ImageButton = itemView.findViewById(R.id.watchlist_delete_button)

        fun bind(stock: WatchListStocks) {
            symbolTextView.text = stock.symbol
            nameTextView.text = stock.name
            typeTextView.text = stock.type
            currencyTextView.text = stock.currency
            priceTextView.text = stock.price
            deleteButton.setOnClickListener {
                viewModel.removeStock(stock)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WatchListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itemwatchlist, parent, false)
        return WatchListViewHolder(view)
    }

    override fun onBindViewHolder(holder: WatchListViewHolder, position: Int) {
        val stock = stocks[position]
        holder.bind(stock)
    }

    override fun getItemCount() = stocks.size

    fun updateStocks(newStocks: List<WatchListStocks>) {
        stocks = newStocks
        notifyDataSetChanged()
    }
}
