package com.example.shoppingcart.cart

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoppingcart.App
import com.example.shoppingcart.productlist.CartProductList
import com.example.shoppingcart.repository.LocalRepository
import javax.inject.Inject

class CartViewModel @Inject constructor(
    private val repository: LocalRepository
) : ViewModel() {
    val _initialOrderNumber = 100
    val _total = MutableLiveData<Int>()
    var _cartCounter = MutableLiveData<Int>()
    val _product = repository.product
    var _checkout = MutableLiveData<Boolean>(false)
    var _confirmed = MutableLiveData<Boolean>(false)


    fun validateOrder(cart: List<CartProductList>) {
        if (!cart.isNullOrEmpty()) {
            _checkout.value = true
        } else {
            Toast.makeText(App.appContext, "Cart is empty", Toast.LENGTH_SHORT).show()
        }
    }

    fun validatePayment(name: String?, email: String?, agree: Boolean) {
        if (!name.isNullOrEmpty() && !email.isNullOrEmpty() && agree){
            _cartCounter.value = 0
            _confirmed.value = true
        } else {
            Toast.makeText(App.appContext, "Please supply needed validation", Toast.LENGTH_SHORT).show()
        }
    }
}