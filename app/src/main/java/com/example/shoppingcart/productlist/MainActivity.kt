package com.example.shoppingcart.productlist

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppingcart.App
import com.example.shoppingcart.R
import com.example.shoppingcart.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_custom_toolbar.*
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

    var counter = 0

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
        viewModel._cartProducts.observe(this, { products ->
            products?.let {
                recyclerViewProduct.layoutManager = layoutManager
                adapter.setData(products as ArrayList<CartProductList>)
            }
        })

        viewModel._cartCounter.observe(this, { count ->
            count?.let {
                if (count > 0) {
                    textViewCartCounter.visibility = View.VISIBLE
                    textViewCartCounter.text = count.toString()
                }
            }
        })
    }

    private fun productOnClick(data: CartProductList) {
        counter++
        viewModel._cartCounter.value = counter
        viewModel.insertProduct(data)
    }
}