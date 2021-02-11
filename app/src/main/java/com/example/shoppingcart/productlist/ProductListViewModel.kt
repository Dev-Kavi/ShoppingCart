package com.example.shoppingcart.productlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoppingcart.repository.LocalRepository
import javax.inject.Inject

class ProductListViewModel @Inject constructor(
    private val repository: LocalRepository
) : ViewModel() {

    val _products = repository.product
    var _cartCounter = MutableLiveData<Int>(0)

    init {
        repository.initMockData()
    }
}