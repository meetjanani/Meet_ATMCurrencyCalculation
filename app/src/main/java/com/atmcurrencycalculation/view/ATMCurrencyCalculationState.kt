package com.atmcurrencycalculation.view

import androidx.lifecycle.MutableLiveData
import com.atmcurrencycalculation.baseCode.BaseState
import com.atmcurrencycalculation.model.ATMCurrencyCalculationModel
import com.atmcurrencycalculation.model.ATMLoadCurrencyModel
import com.atmcurrencycalculation.util.initWith

class ATMCurrencyCalculationState : BaseState {
    val withDrawAmount = MutableLiveData<String>().initWith("")
    val ATMLoadCurrencyModel = MutableLiveData<ATMLoadCurrencyModel>()
    val transactionHistoryList = MutableLiveData<ArrayList<ATMCurrencyCalculationModel>>().initWith(
        arrayListOf()
    )
    val lastTransactionHistory = MutableLiveData<ATMCurrencyCalculationModel?>().initWith(ATMCurrencyCalculationModel())
}