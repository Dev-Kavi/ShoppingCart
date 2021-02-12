package com.example.shoppingcart.cart

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.shoppingcart.App
import com.example.shoppingcart.R
import com.example.shoppingcart.cart.fragments.CartFragment
import com.example.shoppingcart.cart.fragments.PaymentFragment
import com.example.shoppingcart.databinding.ActivityCartBinding
import javax.inject.Inject

class CartActivity : AppCompatActivity(), View.OnClickListener {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel by lazy {
        ViewModelProvider(this, factory).get(CartViewModel::class.java)
    }

    private val cartFragment by lazy {
        CartFragment()
    }

    private val paymentFragment by lazy {
        PaymentFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as App).initAppComponent().inject(this)
        super.onCreate(savedInstanceState)
        supportFragmentManager.beginTransaction().replace(R.id.frameLayoutCart, cartFragment)
            .commit()
        val binding =
            DataBindingUtil.setContentView<ActivityCartBinding>(this, R.layout.activity_cart)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.clickHandler = this
        isCheckedOut()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.imageViewBack -> onBackPressed()
        }
    }

    private fun isCheckedOut() {
        viewModel._checkout.observe(this, { checkout ->
            checkout?.let {
                if (checkout) {
                    val transaction = this.supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.frameLayoutCart, paymentFragment)
                    transaction.commit()
                }
            }
        })
    }
}