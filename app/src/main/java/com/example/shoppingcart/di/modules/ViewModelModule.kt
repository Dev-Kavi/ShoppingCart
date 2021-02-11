package com.example.shoppingcart.di.modules

import androidx.lifecycle.ViewModel
import com.example.shoppingcart.cart.CartViewModel
import com.example.shoppingcart.productlist.ProductListViewModel
import com.example.shoppingcart.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProductListViewModel::class)
    abstract fun bindProductViewModel(viewModel: ProductListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CartViewModel::class)
    abstract fun bindCartViewModel(viewModel: CartViewModel): ViewModel

}