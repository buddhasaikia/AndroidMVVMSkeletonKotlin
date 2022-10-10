package com.androidskeleton.mvvm.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding
import dagger.android.support.DaggerAppCompatActivity

abstract class DaggerBaseActivity<T: ViewBinding> : DaggerAppCompatActivity() {
    private var _viewBinding: T? = null
    protected val viewBinding
        get() = _viewBinding as T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _viewBinding = bindingInflater.invoke(layoutInflater)
        setContentView(_viewBinding?.root)
        initUI()
    }

    abstract fun initUI()

    abstract val bindingInflater: (LayoutInflater) -> T

    override fun onDestroy() {
        _viewBinding = null
        super.onDestroy()
    }
}
