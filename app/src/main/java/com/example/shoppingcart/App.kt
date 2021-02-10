package com.example.shoppingcart

import android.app.Application
import android.content.Context
import com.example.shoppingcart.di.AppComponent
import com.example.shoppingcart.di.DaggerAppComponent
import timber.log.Timber

class App : Application() {

    companion object {
        lateinit var appContext: Context
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        initLogger()
    }

    fun initAppComponent(): AppComponent {
        appComponent = DaggerAppComponent.factory().init(applicationContext)
        return appComponent
    }

    private fun initLogger() {
        Timber.plant(Timber.DebugTree())
    }
}