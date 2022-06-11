package com.atmcurrencycalculation.view

import androidx.recyclerview.widget.LinearLayoutManager
import com.atmcurrencycalculation.R
import com.atmcurrencycalculation.adapter.TransactionListAdapter
import com.atmcurrencycalculation.baseCode.BaseActivity
import com.atmcurrencycalculation.databinding.ATMCurrencyCalculationActivityBinding
import com.atmcurrencycalculation.viewmodel.ATMCurrencyCalculationViewModel
import kotlinx.android.synthetic.main.activity_atm_currency_calculation.withDrawButton
import kotlinx.android.synthetic.main.activity_atm_currency_calculation.withDraweditText
import kotlinx.android.synthetic.main.activity_atm_currency_calculation.yourTransactionList
import org.koin.androidx.viewmodel.ext.android.viewModel


class ATMCurrencyCalculationActivity :
    BaseActivity<ATMCurrencyCalculationActivityBinding, ATMCurrencyCalculationState, ATMCurrencyCalculationViewModel>(
        R.layout.activity_atm_currency_calculation
    ) {

    val adapter = TransactionListAdapter()
    override val viewModel: ATMCurrencyCalculationViewModel by viewModel()
    override fun observeViewState(state: ATMCurrencyCalculationState) {
        state.ATMLoadCurrencyModel.observe(this) {
            if (it?.amount ?: 0 <= 0) {
                viewModel.loadNewAmount(
                    50000,
                    10,
                    25,
                    50,
                    75
                )
            }
        }
        state.transactionHistoryList.observe(this) {
            if (it.isNotEmpty()) {
                yourTransactionList.layoutManager =
                    LinearLayoutManager(this@ATMCurrencyCalculationActivity)
                it.reverse()
                adapter.setList(it)
                yourTransactionList.adapter = adapter
                state.lastTransactionHistory.value = it.last()
            }
        }
    }

    private var strAmount: Int = 0
    var rs2000: String = "0"
    var rs500: String = "0"
    var rs200: String = "0"
    var rs100: String = "0"

    override fun initializeViews() {
        title = "My ATM"
        fetchTransaction()
        withDrawButton.setOnClickListener {
            if (state.withDrawAmount.value?.isEmpty() == true) {
                withDraweditText.error = "Please enter the valid amount"
                return@setOnClickListener
            }
            strAmount = state.withDrawAmount.value.toString().trim().toInt()
            countCurrency(strAmount)

            when {
                strAmount % 100 != 0 -> {
                    withDraweditText.error = "Please enter value in multiple of 100"
                }
                else -> {
                    viewModel.insertData(
                        strAmount,
                        rs2000.toInt(),
                        rs500.toInt(),
                        rs200.toInt(),
                        rs100.toInt()
                    )
                }
            }
        }
    }

    fun countCurrency(amount: Int) {
        rs2000 = "0"
        rs500 = "0"
        rs200 = "0"
        rs100 = "0"
        var amount = amount
        val notes = intArrayOf(2000, 500, 200, 100)
        val noteCounter = IntArray(4)

        // count notes using Greedy approach
        for (i in 0..3) {
            if (amount >= notes[i]) {
                noteCounter[i] = amount / notes[i]
                amount -= noteCounter[i] * notes[i]
            }
        }

        // Print notes
        println("Currency Count ->")
        for (i in 0..3) {
            if (noteCounter[i] != 0) {
                println(
                    notes[i].toString() + " : "
                            + noteCounter[i]
                )
                if (i == 0) {
                    rs2000 = noteCounter[i].toString()
                } else if (i == 1) {
                    rs500 = noteCounter[i].toString()
                } else if (i == 2) {
                    rs200 = noteCounter[i].toString()
                } else if (i == 3) {
                    rs100 = noteCounter[i].toString()
                }
            }
        }
    }

    fun fetchTransaction() {
        viewModel.getTransactionHistory()
        viewModel.fetchATMAmount()
    }
}





