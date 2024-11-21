package com.example.stockwatchlist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.stockwatchlist.R
import com.example.stockwatchlist.api.Stock
import com.example.stockwatchlist.data.WatchListStocks
import com.example.stockwatchlist.viewmodel.WatchListViewModel

class StockAdapter(private var stocks:List<Stock>,private val watchListViewModel: WatchListViewModel):RecyclerView.Adapter<StockAdapter.StockViewHolder>() {
    class StockViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        val symbol: TextView = itemView.findViewById(R.id.stock_symbol)
        val name: TextView = itemView.findViewById(R.id.stock_name)
        val type: TextView = itemView.findViewById(R.id.stock_type)
        val currency: TextView = itemView.findViewById(R.id.stock_currency)
        val price: TextView = itemView.findViewById(R.id.stock_price)
        val addButton: ImageButton = itemView.findViewById(R.id.add_button)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itemstock, parent, false)
        return StockViewHolder(view)
    }

    override fun onBindViewHolder(holder: StockViewHolder, position: Int) {
        val stock = stocks[position]
        holder.symbol.text = stock.symbol
        holder.name.text = stock.name
        holder.type.text = stock.type
        holder.currency.text = stock.currency
        holder.price.text = stock.price
        holder.addButton.setOnClickListener {
            val watchListStocks=WatchListStocks(
                symbol = stock.symbol,
                name = stock.name,
                type = stock.type,
                currency = stock.currency,
                price = stock.price
            )
            watchListViewModel.addStock(watchListStocks)
            Toast.makeText(holder.itemView.context, "${stock.name} added to Watchlist", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return stocks.size
    }
    fun updateStocks(newStocks: List<Stock>) {
        stocks = newStocks
        notifyDataSetChanged() }

}