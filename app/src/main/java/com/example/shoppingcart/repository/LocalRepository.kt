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
import timber.log.Timber
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

    fun insertData(list: CartProductList) {
        database.cartDao.insertProduct(list.asDatabaseModel())
    }

    fun insertCartProduct(data: CartProductList) {
        CoroutineScope(Dispatchers.IO).launch {
            insertData(data)
        }
    }
}