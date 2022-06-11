package com.atmcurrencycalculation.viewmodel

import com.atmcurrencycalculation.baseCode.BaseState
import com.atmcurrencycalculation.baseCode.BaseViewModel
import com.atmcurrencycalculation.model.ATMCurrencyCalculationModel
import com.atmcurrencycalculation.model.ATMLoadCurrencyModel
import com.atmcurrencycalculation.room.DAOATMCurrencyCalculation
import com.atmcurrencycalculation.room.DAOATMLoadCurrency
import com.atmcurrencycalculation.view.ATMCurrencyCalculationState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import org.koin.core.component.inject

class ATMCurrencyCalculationViewModel(val state: ATMCurrencyCalculationState) : BaseViewModel() {

    private val atmCurrencyCalculationDao: DAOATMCurrencyCalculation by inject()
    private val atmLoadCurrencyDao: DAOATMLoadCurrency by inject()
    override fun provideState(): BaseState = state

    fun insertData(amount: Int, rs2000: Int, rs500: Int, rs200: Int, rs100: Int) {
        CoroutineScope(IO).launch {
            val loginDetails = ATMCurrencyCalculationModel(amount, rs2000, rs500, rs200, rs100)
            atmCurrencyCalculationDao.insertData(loginDetails)
            state.ATMLoadCurrencyModel.value?.apply {
                this.amount = this.amount - amount
                this.rs100 = this.rs100 - rs100
                this.rs200 = this.rs200 - rs200
                this.rs500 = this.rs500 - rs500
                this.rs2000 = this.rs2000 - rs2000
            }
            getTransactionHistory()
            updateATMBalance()
        }
    }

    fun loadNewAmount(amount: Int, rs2000: Int, rs500: Int, rs200: Int, rs100: Int) {
        CoroutineScope(IO).launch {
            val loginDetails = ATMLoadCurrencyModel(amount, rs2000, rs500, rs200, rs100, 1)
            atmLoadCurrencyDao.InsertData(loginDetails)
            fetchATMAmount()
        }
    }

    fun getTransactionHistory() {
        CoroutineScope(IO).launch {
            state.transactionHistoryList.postValue((atmCurrencyCalculationDao.getAllTransactions() as ArrayList<ATMCurrencyCalculationModel>))
        }
    }

    fun fetchATMAmount() {
        CoroutineScope(IO).launch {
            state.ATMLoadCurrencyModel.postValue(atmLoadCurrencyDao.getLastTransactionDetails(1))
        }
    }
    fun updateATMBalance() {
        CoroutineScope(IO).launch {
            atmLoadCurrencyDao.UpdateData(state.ATMLoadCurrencyModel.value!!)
            fetchATMAmount()
        }
    }
}