package com.androidskeleton.mvvm.data.datasource.base

import androidx.lifecycle.ViewModel

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel : ViewModel() {
    private val mCompositeDisposable: CompositeDisposable = CompositeDisposable()

    fun addSubscription(d: Disposable) {
        mCompositeDisposable.add(d)
    }

    override fun onCleared() {
        super.onCleared()
        mCompositeDisposable.clear()
    }
}
