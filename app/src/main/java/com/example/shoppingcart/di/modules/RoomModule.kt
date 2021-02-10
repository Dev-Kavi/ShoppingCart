package com.example.shoppingcart.di.modules

import androidx.room.Room
import com.example.shoppingcart.App
import com.example.shoppingcart.di.RoomShoppingCart
import com.example.shoppingcart.room.CartDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Provides
    @Singleton
    @RoomShoppingCart
    fun provideCartDatabase(): CartDatabase {
        return Room.databaseBuilder(
            App.appContext,
            CartDatabase::class.java,
            "cart_database"
        ).build()
    }

    @Provides
    fun provideDatabase(@RoomShoppingCart database: CartDatabase): CartDatabase = database
}