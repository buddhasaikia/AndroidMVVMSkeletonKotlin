package com.androidskeleton.mvvm.util

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

import com.androidskeleton.mvvm.data.repository.Repository
import com.androidskeleton.mvvm.module.main.MainViewModel

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CustomViewModelFactory @Inject
constructor(private val repository: Repository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java))
            MainViewModel(repository) as T
        else
            throw IllegalArgumentException("ViewModel not found!")
    }
}
