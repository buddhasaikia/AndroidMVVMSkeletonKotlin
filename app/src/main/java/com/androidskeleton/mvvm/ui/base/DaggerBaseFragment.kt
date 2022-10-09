package com.androidskeleton.mvvm.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

import dagger.android.support.DaggerFragment


abstract class DaggerBaseFragment<T: ViewBinding> : DaggerFragment() {

    private var binding: T? = null
    protected val viewBinding: T get() = binding as T

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = bindingInflater.invoke(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    abstract fun initUI()

    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> T

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
