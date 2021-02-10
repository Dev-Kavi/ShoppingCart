package com.example.shoppingcart.productlist

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppingcart.App
import com.example.shoppingcart.R
import com.example.shoppingcart.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel by lazy {
        ViewModelProvider(this, factory).get(ProductListViewModel::class.java)
    }

    private val adapter by lazy {
        ProductListRecyclerViewAdapter(::productOnClick)
    }

    private val layoutManager by lazy {
        LinearLayoutManager(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as App).initAppComponent().inject(this)
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.recyclerViewProduct.adapter = adapter
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        observeViewModels()
    }

    private fun observeViewModels() {
        viewModel._products.observe(this, { products ->
            products?.let {
                recyclerViewProduct.layoutManager = layoutManager
                adapter.setData(products as ArrayList<CartProductList>)
            }
        })
    }

    private fun productOnClick(id: String) {

    }
}