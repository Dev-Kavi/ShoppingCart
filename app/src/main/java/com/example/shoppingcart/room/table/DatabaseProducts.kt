package com.example.shoppingcart.room.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.shoppingcart.productlist.CartProductList

@Entity(tableName = "product_table")
data class DatabaseProductsTable(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: String,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "category")
    var category: String,

    @ColumnInfo(name = "price")
    var price: Double,

    @ColumnInfo(name = "bgColor")
    var bgColor: String
)

fun List<DatabaseProductsTable>.asDomainModel(): List<CartProductList> {
    return map {
        CartProductList(
            id = it.id,
            name = it.name,
            category = it.category,
            price = it.price,
            bgColor = it.bgColor
        )
    }
}
