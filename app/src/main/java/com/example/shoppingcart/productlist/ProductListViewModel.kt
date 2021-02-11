package com.example.shoppingcart.productlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoppingcart.repository.LocalRepository
import timber.log.Timber
import javax.inject.Inject

class ProductListViewModel @Inject constructor(
    private val repository: LocalRepository
) : ViewModel() {
    val _cartProducts = MutableLiveData<List<CartProductList>>()
    var _cartCounter = MutableLiveData<Int>()

    init {
        initSampleList()
    }

    private fun initSampleList() {
        val list = ArrayList<CartProductList>()
        list.add(CartProductList("p_1", "Flash T-Shirt", "Tee", 75.00, "#F95A2C"))
        list.add(CartProductList("p_2", "Fur-neck Jacket", "Jacket", 315.00, "#00C6AE"))
        list.add(CartProductList("p_3", "Everyday Classic Blazer", "Blazer", 200.00, "#FFBD12"))
        list.add(CartProductList("p_4", "White striped T-Shirt", "Tee", 78.00, "#FF89BB"))
        list.add(CartProductList("p_5", "Grey striped T-Shirt", "Tee", 112.00, "#1947E5"))
        _cartProducts.value = list
    }

    fun insertProduct(data: CartProductList) {
        repository.insertCartProduct(data)
    }

    fun deleteProduct() {
        repository.dropTable()
    }
}