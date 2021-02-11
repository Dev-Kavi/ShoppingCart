package com.example.shoppingcart.productlist

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.api.load
import com.example.shoppingcart.R

@BindingAdapter("listImage")
fun bindListImage(imageView: ImageView, content: CartProductList) {
    imageView.load(setImage(content.id))
    imageView.setBackgroundResource(setBackgroundTint(content.bgColor))
}

fun setBackgroundTint(tint: String): Int {
    var color = R.color.white
    when (tint) {
        "#F95A2C" -> color = R.color.product_background_a
        "#00C6AE" -> color = R.color.product_background_b
        "#FFBD12" -> color = R.color.product_background_c
        "#FF89BB" -> color = R.color.product_background_d
        "#1947E5" -> color = R.color.product_background_e
    }
    return color
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