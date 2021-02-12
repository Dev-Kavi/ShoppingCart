package com.example.shoppingcart.cart

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoppingcart.App
import com.example.shoppingcart.repository.LocalRepository
import javax.inject.Inject

class CartViewModel @Inject constructor(
    private val repository: LocalRepository
) : ViewModel() {
    val _total = MutableLiveData<Int>()
    val _product = repository.product
    var _checkout = MutableLiveData<Boolean>(false)
    var _confirmed = MutableLiveData<Boolean>(false)


    fun validateOrder(name: String?, email: String?, agree: Boolean) {
        if (!name.isNullOrEmpty() && !email.isNullOrEmpty() && agree){
            _confirmed.value = true
        } else {
            Toast.makeText(App.appContext, "Please fill up all form", Toast.LENGTH_SHORT).show()
        }
    }
}