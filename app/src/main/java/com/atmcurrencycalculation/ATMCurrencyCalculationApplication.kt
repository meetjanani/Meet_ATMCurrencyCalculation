package com.atmcurrencycalculation

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ATMCurrencyCalculationApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(applicationContext)
            modules(listOf(stateModule, viewmodelModule, databaseModule, daoModule))
        }
    }
}