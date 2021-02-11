package com.example.shoppingcart.productlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingcart.databinding.ProductListItemLayoutBinding

class ProductListRecyclerViewAdapter(
    private val productOnClick: (CartProductList) -> Unit
) : RecyclerView.Adapter<ProductListRecyclerViewAdapter.ProductViewHolder>() {

    private var list: ArrayList<CartProductList> = ArrayList()

    fun setData(list: ArrayList<CartProductList>) {
        this.list = list
        notifyDataSetChanged()
    }

    class ProductViewHolder(
        private val binding: ProductListItemLayoutBinding,
        private val clickUser: (CartProductList) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(
                parent: ViewGroup,
                clickProduct: (CartProductList) -> Unit
            ): ProductViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ProductListItemLayoutBinding.inflate(layoutInflater, parent, false)

                return ProductViewHolder(binding, clickProduct)
            }
        }

        fun setDataBinding(data: CartProductList) {
            binding.content = data
            binding.buttonAddProduct.setOnClickListener { clickUser(data) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val holder: RecyclerView.ViewHolder
        holder = ProductViewHolder.from(parent, productOnClick)
        return holder
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val list = list[position]
        holder.setDataBinding(list)
    }

    override fun getItemCount(): Int = list.size
}