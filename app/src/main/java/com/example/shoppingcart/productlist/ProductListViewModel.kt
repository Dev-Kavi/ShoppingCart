package com.example.shoppingcart.productlist

import androidx.lifecycle.ViewModel
import com.example.shoppingcart.repository.LocalRepository
import javax.inject.Inject

class ProductListViewModel @Inject constructor(
    private val repository: LocalRepository
) : ViewModel() {

    val _products = repository.product

    init {
        repository.initMockData()
    }
}