package com.atmcurrencycalculation

import androidx.room.Room
import com.atmcurrencycalculation.room.ATMCurrencyCalculationDatabase
import com.atmcurrencycalculation.view.ATMCurrencyCalculationState
import com.atmcurrencycalculation.viewmodel.ATMCurrencyCalculationViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


var stateModule = module {
    factory { ATMCurrencyCalculationState() }
}


var viewmodelModule = module {
    factory { ATMCurrencyCalculationViewModel(get()) }
}

var databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            ATMCurrencyCalculationDatabase::class.java,
            "ATM_DATABASE"
        )
            .fallbackToDestructiveMigration().build()
    }
}

var daoModule = module {
    single { get<ATMCurrencyCalculationDatabase>().currencyCalculationDao() }
    single { get<ATMCurrencyCalculationDatabase>().loadCurrencyDao() }
}