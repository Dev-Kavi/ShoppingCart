package com.example.shoppingcart.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.shoppingcart.productlist.CartProductList
import com.example.shoppingcart.productlist.asDatabaseModel
import com.example.shoppingcart.room.CartDatabase
import com.example.shoppingcart.room.table.asDomainModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalRepository @Inject constructor(
    private val database: CartDatabase
) {

    val product: LiveData<List<CartProductList>> =
        Transformations.map(database.cartDao.getAllProduct()) {
            it.asDomainModel()
        }

    fun insertMockData(list: List<CartProductList>) {
        database.cartDao.insertAllProduct(list.asDatabaseModel())
    }

    fun initMockData() {
        CoroutineScope(Dispatchers.IO).launch {
            val list = ArrayList<CartProductList>()
            list.add(CartProductList("p_1", "Flash T-Shirt", "Tee", 75.00, "#F95A2C"))
            list.add(CartProductList("p_2", "Fur-neck Jacket", "Jacket", 315.00, "#00C6AE"))
            list.add(CartProductList("p_3", "Everyday Classic Blazer", "Blazer", 200.00, "#FFBD12"))
            list.add(CartProductList("p_4", "White striped T-Shirt", "Tee", 78.00, "#FF89BB"))
            list.add(CartProductList("p_5", "Grey striped T-Shirt", "Tee", 112.00, "#1947E5"))
            insertMockData(list)
        }
    }
}