
package com.vnguy23.mystocktracker.ui

import android.annotation.SuppressLint
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vnguy23.mystocktracker.R
import com.vnguy23.mystocktracker.database.Stock
import com.vnguy23.mystocktracker.databinding.StockItemBinding

class StockAdapter : RecyclerView.Adapter<StockAdapter.StockViewHolder>() {
    lateinit var res: Resources
    class StockViewHolder(private val binding: StockItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(stock: Stock) {
            binding.apply {
                stockCurrentPriceEdit.text = stock.current_price
                stockBoughtPriceEdit.text = stock.bought_price
                stockCode.text = stock.stock_code
                stockCommentEdit.text = stock.comment
                stockAmountEdit.text = stock.amount
                stockBoughtPrice.setText(R.string.bought_price)
                stockAmount.setText(R.string.amount)
                stockCurrentPrice.setText(R.string.current_price)
                stockComment.setText(R.string.comment)
            }
        }
    }

    private var stocks: List<Stock> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = StockViewHolder(
        StockItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: StockViewHolder, position: Int) {
        holder.bind(stocks[position])
    }

    fun getStockAtPosition(position: Int): Stock {
        return stocks[position]
    }

    override fun getItemCount() = stocks.size

    fun getItemAtPosition(position: Int) = stocks[position]

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(newStock: List<Stock>) {
        this.stocks = newStock
        notifyDataSetChanged()
    }
}