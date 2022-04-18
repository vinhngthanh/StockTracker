package com.vnguy23.mystocktracker.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.vnguy23.mystocktracker.database.Stock
import com.vnguy23.mystocktracker.database.StockRepository

class MainViewModel(application: Application) : AndroidViewModel(application) {

    init {
        StockRepository.initialize(application)
    }

    private val stockRepository = StockRepository.get()
    val mediaLiveData = stockRepository.getAllStocks()

    fun addStock(newStock: Stock) {
        stockRepository.addStock(newStock)
    }

    fun deleteStock(stock: Stock) {
        stockRepository.deleteStock(stock)
    }
}