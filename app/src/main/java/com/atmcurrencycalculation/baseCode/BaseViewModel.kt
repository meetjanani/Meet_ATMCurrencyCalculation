package com.atmcurrencycalculation.baseCode

import androidx.lifecycle.ViewModel
import org.koin.core.component.KoinComponent

abstract class BaseViewModel : ViewModel(), KoinComponent {
    abstract fun provideState(): BaseState
}