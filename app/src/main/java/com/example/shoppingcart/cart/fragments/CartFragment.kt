package com.example.shoppingcart.cart.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppingcart.App
import com.example.shoppingcart.R
import com.example.shoppingcart.cart.CartRecyclerViewAdapter
import com.example.shoppingcart.cart.CartViewModel
import com.example.shoppingcart.databinding.FragmentCartBinding
import com.example.shoppingcart.productlist.CartProductList
import kotlinx.android.synthetic.main.fragment_cart.*
import javax.inject.Inject

class CartFragment : Fragment(), View.OnClickListener {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private lateinit var binding: FragmentCartBinding

    private val viewModel by lazy {
        ViewModelProvider(this.requireActivity(), factory).get(CartViewModel::class.java)
    }

    private val adapter by lazy {
        CartRecyclerViewAdapter(::orderOnClick)
    }

    private val layoutManager by lazy {
        LinearLayoutManager(this.requireContext())
    }

    val cartItem = ArrayList<CartProductList>()

    override fun onAttach(context: Context) {
        (requireActivity().application as App).initAppComponent().inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        binding.clickHandler = this
        binding.viewModel = viewModel
        binding.recyclerViewCart.adapter = adapter
        return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initCartList()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.buttonFragmentBuyNow -> validateOrder()
        }
    }

    private fun initCartList() {
        viewModel._product.observe(viewLifecycleOwner, { product ->
            product?.let {
                viewModel._total.value = product.map { it.price }.sum().toInt()
                viewModel._cartCounter.value = product.size
                cartItem.addAll(product)
                recyclerViewCart.layoutManager = layoutManager
                adapter.setData(product as ArrayList<CartProductList>)
            }
        })
    }

    private fun validateOrder() {
        viewModel.validateOrder(cartItem)
    }

    private fun orderOnClick(data: CartProductList) {
        //TODO ADD PRODUCT DELETION ON CART
    }
}