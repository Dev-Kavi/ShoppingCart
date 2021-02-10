package com.example.shoppingcart.room.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_table")
data class DatabaseProductsTable(

        @PrimaryKey
        @ColumnInfo(name = "id")
        var id: Int,

        @ColumnInfo(name = "name")
        var name: String,

        @ColumnInfo(name = "category")
        var category: String,

        @ColumnInfo(name = "price")
        var price: Double,

        @ColumnInfo(name = "bgColor")
        var bgColor: String
)
