package com.example.shoppingcart.cart.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.shoppingcart.App
import com.example.shoppingcart.R
import com.example.shoppingcart.cart.CartViewModel
import com.example.shoppingcart.databinding.FragmentPaymentBinding
import kotlinx.android.synthetic.main.fragment_payment.*
import javax.inject.Inject


class PaymentFragment : Fragment(), View.OnClickListener {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private lateinit var binding: FragmentPaymentBinding

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
        binding = FragmentPaymentBinding.inflate(inflater, container, false)
        binding.clickHandler = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.buttonPayout -> validateOrder()
        }
    }

    private fun validateOrder() {
        val hasAgree = switchButton.isChecked
        val name = editTexName.text.toString()
        val email = editTexEmail.text.toString()
        viewModel.validatePayment(name, email, hasAgree)
    }
}