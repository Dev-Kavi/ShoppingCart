package com.example.shoppingcart.productlist

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.api.load
import com.example.shoppingcart.R

@BindingAdapter("listImage")
fun bindListImage(imageView: ImageView, id: String) {
    imageView.load(setImage(id))
}

fun setImage(id: String): Int {
    var drawable = R.drawable.ic_p1
    when (id) {
        "p_1" -> drawable = R.drawable.ic_p1
        "p_2" -> drawable = R.drawable.ic_p2
        "p_3" -> drawable = R.drawable.ic_p3
        "p_4" -> drawable = R.drawable.ic_p4
        "p_5" -> drawable = R.drawable.ic_p5
    }
    return drawable
}

@BindingAdapter("listPrice")
fun bindListPrice(textView: TextView, price: Double) {
    textView.text = textView.resources.getString(R.string.product_list_product_price, price.toInt())
}