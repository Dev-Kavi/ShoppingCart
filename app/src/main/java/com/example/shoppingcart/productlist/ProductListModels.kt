package com.example.shoppingcart.productlist

import com.example.shoppingcart.room.table.DatabaseProductsTable

data class CartProductList(
    var id: String,
    var name: String,
    var category: String,
    var price: Double,
    var bgColor: String
)

fun CartProductList.asDatabaseModel(): DatabaseProductsTable =
    DatabaseProductsTable(
        id = id,
        name = name,
        category = category,
        price = price,
        bgColor = bgColor
    )
