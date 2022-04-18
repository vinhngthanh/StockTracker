
package com.vnguy23.mystocktracker.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stocks_table")
data class Stock(

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var stock_code: String = "",
    var amount: String = "",
    var current_price: String = "",
    var bought_price: String = "",
    var comment: String = "",
)