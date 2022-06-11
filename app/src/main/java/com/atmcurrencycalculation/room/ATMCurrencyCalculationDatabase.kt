package com.atmcurrencycalculation.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.atmcurrencycalculation.model.ATMCurrencyCalculationModel
import com.atmcurrencycalculation.model.ATMLoadCurrencyModel

@Database(entities = [ATMCurrencyCalculationModel::class, ATMLoadCurrencyModel::class], version = 1, exportSchema = false)
abstract class ATMCurrencyCalculationDatabase : RoomDatabase() {

    abstract fun currencyCalculationDao() : DAOATMCurrencyCalculation
    abstract fun loadCurrencyDao() : DAOATMLoadCurrency

}