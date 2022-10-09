package com.androidskeleton.mvvm.di.module


import com.androidskeleton.mvvm.di.scope.PerActivity
import com.androidskeleton.mvvm.ui.main.MainActivity
import com.androidskeleton.mvvm.ui.main.MainModule

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {
    @PerActivity
    @ContributesAndroidInjector(modules = [MainModule::class])
    internal abstract fun mainActivity(): MainActivity
}
