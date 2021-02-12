package com.example.shoppingcart.cart.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.shoppingcart.App
import com.example.shoppingcart.R
import com.example.shoppingcart.cart.CartViewModel
import com.example.shoppingcart.databinding.OrderConfirmationFragmentBinding
import com.example.shoppingcart.productlist.MainActivity
import kotlinx.android.synthetic.main.cart_custom_toolbar.*
import kotlinx.android.synthetic.main.order_confirmation_fragment.view.*
import javax.inject.Inject

class OrderConfirmationFragment : Fragment(), View.OnClickListener {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    lateinit var binding: OrderConfirmationFragmentBinding

    private val viewModel by lazy {
        ViewModelProvider(this.requireActivity(), factory).get(CartViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        (requireActivity().application as App).initAppComponent().inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = OrderConfirmationFragmentBinding.inflate(inflater, container, false)
        binding.clickHandler = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.buttonReturnToProducts -> initProductScreen()
        }
    }

    private fun initProductScreen() {
        val intent = Intent(Intent(this.requireActivity(), MainActivity::class.java))
        startActivity(intent)
    }

}