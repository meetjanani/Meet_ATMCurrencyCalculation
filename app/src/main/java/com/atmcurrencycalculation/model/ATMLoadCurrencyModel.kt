package com.atmcurrencycalculation.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "LoadCurrency")
data class ATMLoadCurrencyModel(

    @ColumnInfo(name = "amount")
    var amount: Int = 0,

    @ColumnInfo(name = "rs2000")
    var rs2000: Int = 0,

    @ColumnInfo(name = "rs500")
    var rs500: Int = 0,

    @ColumnInfo(name = "rs200")
    var rs200: Int = 0,

    @ColumnInfo(name = "rs100")
    var rs100: Int = 0,

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var Id: Int = 0

)