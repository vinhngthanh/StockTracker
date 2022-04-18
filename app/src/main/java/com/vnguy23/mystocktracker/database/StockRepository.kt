package com.vnguy23.mystocktracker.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import java.util.concurrent.Executors

class StockRepository private constructor(context: Context) {

    private val database: StockDatabase = Room.databaseBuilder(
        context.applicationContext,
        StockDatabase::class.java,
        "stock_database"
    ).build()

    private val stockDao = database.stockDao()
    private val executor = Executors.newSingleThreadExecutor()

    fun addStock(stock: Stock) {
        executor.execute {
            stockDao.addStock(stock)
        }
    }

    fun updateStock(stock: Stock) {
        executor.execute {
            stockDao.updateStock(stock)
        }
    }

    fun deleteStock(stock: Stock) {
        executor.execute {
            stockDao.deleteStock(stock)
        }
    }

    fun getStock(id: Long): LiveData<Stock?> = stockDao.getStock(id)

    fun getAllStocks(): LiveData<List<Stock>> = stockDao.getAllStocks()

    companion object {

        private var INSTANCE: StockRepository? = null
        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = StockRepository(context)
            }
        }

        fun get(): StockRepository {
            return INSTANCE
                ?: throw IllegalStateException("StockRepository must be initialized.")
        }
    }
}
