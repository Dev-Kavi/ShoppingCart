package com.example.shoppingcart.cart

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppingcart.App
import com.example.shoppingcart.R
import com.example.shoppingcart.databinding.ActivityCartBinding
import com.example.shoppingcart.productlist.CartProductList
import kotlinx.android.synthetic.main.activity_cart.*
import javax.inject.Inject

class CartActivity : AppCompatActivity(), View.OnClickListener {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel by lazy {
        ViewModelProvider(this, factory).get(CartViewModel::class.java)
    }

    private val adapter by lazy {
        CartRecyclerViewAdapter(::orderOnClick)
    }

    private val layoutManager by lazy {
        LinearLayoutManager(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as App).initAppComponent().inject(this)
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityCartBinding>(this, R.layout.activity_cart)
        binding.recyclerViewCart.adapter = adapter
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.clickHandler = this
        observeViewModel()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.imageViewBack -> onBackPressed()
        }
    }

    private fun observeViewModel() {
        viewModel._product.observe(this, { list ->
            list?.let {
                viewModel._total.value = list.map { it.price }.sum().toInt()
                recyclerViewCart.layoutManager = layoutManager
                adapter.setData(list as ArrayList<CartProductList>)
            }
        })
    }

    private fun orderOnClick(data: CartProductList) {

    }
}