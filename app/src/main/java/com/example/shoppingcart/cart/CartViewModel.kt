package com.example.shoppingcart.cart

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoppingcart.repository.LocalRepository
import javax.inject.Inject

class CartViewModel @Inject constructor(
    private val repository: LocalRepository
) : ViewModel() {
    val _total = MutableLiveData<Int>()
    val _product = repository.product
    var _checkout = MutableLiveData<Boolean>(false)

}