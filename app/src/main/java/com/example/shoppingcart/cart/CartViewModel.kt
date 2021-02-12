package com.example.shoppingcart.cart

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingcart.App
import com.example.shoppingcart.R
import com.example.shoppingcart.productlist.CartProductList
import com.example.shoppingcart.repository.LocalRepository
import kotlinx.coroutines.launch
import javax.inject.Inject


class CartViewModel @Inject constructor(
    repository: LocalRepository
) : ViewModel() {
    val _orderNumber = (100..1000).random()
    val _total = MutableLiveData<Int>()
    var _cartCounter = MutableLiveData<Int>()
    val _product = repository.product
    var _checkout = MutableLiveData<Boolean>(false)
    var _confirmed = MutableLiveData<Boolean>(false)


    fun validateOrder(cart: List<CartProductList>) {
        viewModelScope.launch {
            if (!cart.isNullOrEmpty()) {
                _checkout.value = true
            } else {
                Toast.makeText(
                    App.appContext,
                    App.appContext.resources.getString(R.string.local_error_empty_cart),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    fun validatePayment(name: String?, email: String?, agree: Boolean) {
        viewModelScope.launch {
            if (!name.isNullOrEmpty() && !email.isNullOrEmpty() && agree) {
                _cartCounter.value = 0
                _confirmed.value = true
            } else {
                Toast.makeText(
                    App.appContext,
                    App.appContext.resources.getString(R.string.local_error_invalid_order),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}