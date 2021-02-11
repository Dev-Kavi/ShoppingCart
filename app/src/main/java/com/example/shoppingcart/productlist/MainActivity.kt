package com.example.shoppingcart.productlist

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingcart.App
import com.example.shoppingcart.R
import com.example.shoppingcart.cart.CartActivity
import com.example.shoppingcart.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.main_custom_toolbar.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), View.OnClickListener {

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

    private val recyclerView by lazy {
        findViewById<RecyclerView>(R.id.recyclerViewProduct)
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
        binding.clickHandler = this
        viewModel.deleteProduct()
        observeViewModels()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.imageViewCart -> initCartScreen()
        }
    }

    private fun observeViewModels() {
        viewModel._cartProducts.observe(this, { products ->
            products?.let {
                recyclerView.layoutManager = layoutManager
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

    private fun initCartScreen() {
        val intent = Intent(Intent(this, CartActivity::class.java))
        startActivity(intent)
    }
}