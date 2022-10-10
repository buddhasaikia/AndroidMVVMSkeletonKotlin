package com.androidskeleton.mvvm.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

import dagger.android.support.DaggerFragment


abstract class DaggerBaseFragment<T: ViewBinding> : DaggerFragment() {

    private var _viewBinding: T? = null
    val viewBinding
        get() = _viewBinding as T

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _viewBinding = bindingInflater.invoke(inflater, container, false)
        return _viewBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    abstract fun initUI()

    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> T

    override fun onDestroyView() {
        _viewBinding = null
        super.onDestroyView()
    }
}
