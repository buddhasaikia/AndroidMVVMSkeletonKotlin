package com.androidskeleton.mvvm.module.main

import com.androidskeleton.mvvm.data.datasource.base.BaseViewModel
import com.androidskeleton.mvvm.data.datasource.base.DataSource
import com.androidskeleton.mvvm.data.repository.Repository

import io.reactivex.Observable

class MainViewModel(private val repository: Repository) : BaseViewModel(), DataSource.Greetings {

    override fun greetings(): Observable<String>? {
        return repository.greetings()
    }
}
