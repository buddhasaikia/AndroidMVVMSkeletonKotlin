package com.androidskeleton.mvvm.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<T : ViewBinding> : AppCompatActivity() {

    private var binding: T? = null
    protected val viewBinding get() = binding?.root

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = bindingInflater.invoke(layoutInflater)
        setContentView(binding?.root)
        initUI()
    }

    abstract fun initUI()

    abstract val bindingInflater: (LayoutInflater) -> T

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}
