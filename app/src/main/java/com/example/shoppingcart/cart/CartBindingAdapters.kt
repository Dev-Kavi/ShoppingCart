package com.example.shoppingcart.cart

import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.example.shoppingcart.R
import com.example.shoppingcart.productlist.setBackgroundTint

@BindingAdapter("cartProductPrice")
fun bindCartProductPrice(textView: TextView, price: Double) {
    textView.text = textView.resources.getString(R.string.product_list_product_price, price.toInt())
}

@BindingAdapter("cartProductTotal")
fun bindCartProductTotal(textView: TextView, price: Int) {
    textView.text = textView.resources.getString(R.string.product_list_product_price, price)
}

@BindingAdapter("cartCheckoutTotal")
fun bindCartCheckoutTotal(textView: TextView, price: Int) {
    textView.text = textView.resources.getString(R.string.product_list_product_checkout_price, price)
}

@BindingAdapter("layoutBackground")
fun bindLayoutBackground(layout: ConstraintLayout, color: String) {
    layout.setBackgroundResource(setBackgroundTint(color))
}

@BindingAdapter("cartOrderNumber")
fun bindCartOrderNumber(textView: TextView, number : Int) {
    textView.text = textView.resources.getString(R.string.cart_order_number, number)
}

