package com.atmcurrencycalculation.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.atmcurrencycalculation.model.ATMCurrencyCalculationModel

@Dao
interface DAOATMCurrencyCalculation {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(atmCurrencyCalculationModel: ATMCurrencyCalculationModel)

    @Query("SELECT * FROM `Transaction` WHERE amount =:amount")
    fun getLastTransactionDetails(amount: Int?): LiveData<ATMCurrencyCalculationModel>

    @Query(
        """SELECT
					*
				FROM
					`Transaction`"""
    )
    suspend fun getAllTransactions(): List<ATMCurrencyCalculationModel>

}