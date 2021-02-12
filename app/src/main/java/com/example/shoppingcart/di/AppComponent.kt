package com.example.shoppingcart.di

import android.content.Context
import com.example.shoppingcart.cart.CartActivity
import com.example.shoppingcart.cart.fragments.CartFragment
import com.example.shoppingcart.di.modules.RoomModule
import com.example.shoppingcart.di.modules.ViewModelModule
import com.example.shoppingcart.productlist.MainActivity
import com.example.shoppingcart.viewmodel.ViewModelBuilder
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [RoomModule::class,
        ViewModelModule::class,
        ViewModelBuilder::class]
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun init(@BindsInstance context: Context): AppComponent
    }

    fun inject(main: MainActivity)
    fun inject(cart: CartActivity)
    fun inject(fragmentCart: CartFragment)
}