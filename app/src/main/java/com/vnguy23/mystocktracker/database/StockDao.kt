
package com.vnguy23.mystocktracker.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface StockDao {

    @Insert
    fun addStock(stock: Stock)

    @Update
    fun updateStock(stock: Stock)

    @Delete
    fun deleteStock(stock: Stock)

    @Query("SELECT * FROM stocks_table WHERE id=(:id)")
    fun getStock(id: Long): LiveData<Stock?>

    @Query("SELECT * FROM stocks_table ORDER BY id DESC")
    fun getAllStocks(): LiveData<List<Stock>>

    @Query("SELECT * FROM stocks_table ORDER BY id DESC LIMIT 1")
    fun getLastStock(): LiveData<Stock?>
}
