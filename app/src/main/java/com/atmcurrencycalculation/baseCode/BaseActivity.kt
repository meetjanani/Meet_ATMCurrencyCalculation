package com.atmcurrencycalculation.baseCode

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.atmcurrencycalculation.BR

abstract class BaseActivity<BINDING: ViewDataBinding, STATE : BaseState, VIEWMODEL : BaseViewModel>(@LayoutRes val layoutId: Int) :
    AppCompatActivity() {

    protected abstract val viewModel: VIEWMODEL
    protected val state: STATE by lazy {
        viewModel.provideState() as STATE
    }
    protected lateinit var binding: BINDING

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        initializeDataBinding()
        initializeViews()
        observeViewState(state)
    }

    open fun initializeViews() {}

    private fun initializeDataBinding() {
        DataBindingUtil.setContentView<BINDING>(this, layoutId)
            .apply {
                binding = this
                lifecycleOwner = this@BaseActivity
                setVariable(BR.viewModel, viewModel)
                setVariable(BR.state, state)
                executePendingBindings()
            }
    }

    abstract fun observeViewState(state: STATE)
}