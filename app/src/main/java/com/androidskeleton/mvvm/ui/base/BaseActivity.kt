package com.androidskeleton.mvvm.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<T : ViewBinding> : AppCompatActivity() {

    private var _viewBinding: T? = null
    protected val viewBinding get() = _viewBinding as T

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
