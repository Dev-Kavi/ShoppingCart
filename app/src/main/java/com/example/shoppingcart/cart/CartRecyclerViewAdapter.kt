package com.example.shoppingcart.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingcart.databinding.CartListItemLayoutBinding
import com.example.shoppingcart.productlist.CartProductList

class CartRecyclerViewAdapter(
    private val productOnClick: (CartProductList) -> Unit
) : RecyclerView.Adapter<CartRecyclerViewAdapter.CartViewHolder>() {

    private var list: ArrayList<CartProductList> = ArrayList()

    fun setData(list: ArrayList<CartProductList>) {
        this.list = list
        notifyDataSetChanged()
    }

    class CartViewHolder(
        private val binding: CartListItemLayoutBinding,
        private val clickUser: (CartProductList) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(
                parent: ViewGroup,
                clickProduct: (CartProductList) -> Unit
            ): CartViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CartListItemLayoutBinding.inflate(layoutInflater, parent, false)

                return CartViewHolder(binding, clickProduct)
            }
        }

        fun setDataBinding(data: CartProductList) {
            binding.content = data
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val holder: RecyclerView.ViewHolder
        holder = CartViewHolder.from(parent, productOnClick)
        return holder
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val list = list[position]
        holder.setDataBinding(list)
    }

    override fun getItemCount(): Int = list.size
}