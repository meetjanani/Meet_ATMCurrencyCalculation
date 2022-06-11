package com.atmcurrencycalculation.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.atmcurrencycalculation.model.ATMCurrencyCalculationModel
import com.atmcurrencycalculation.model.ATMLoadCurrencyModel

@Dao
interface DAOATMLoadCurrency {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertData(loginTableModel: ATMLoadCurrencyModel)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun UpdateData(loginTableModel: ATMLoadCurrencyModel)

    @Query("SELECT * FROM `LoadCurrency` WHERE id =:id")
    suspend fun getLastTransactionDetails(id: Int?): ATMLoadCurrencyModel

    @Query(
        """SELECT
					*
				FROM
					`LoadCurrency`"""
    )
    fun getAllTransactions(): LiveData<List<ATMLoadCurrencyModel>>

}