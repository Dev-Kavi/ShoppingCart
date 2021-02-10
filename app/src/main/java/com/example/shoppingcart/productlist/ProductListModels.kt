package com.example.shoppingcart.productlist

import com.example.shoppingcart.room.table.DatabaseProductsTable

data class CartProductList(
    var id: String,
    var name: String,
    var category: String,
    var price: Double,
    var bgColor: String
)

fun List<CartProductList>.asDatabaseModel(): List<DatabaseProductsTable> {
    return map {
        DatabaseProductsTable(
            id = it.id,
            name = it.name,
            category = it.category,
            price = it.price,
            bgColor = it.bgColor
        )
    }
}