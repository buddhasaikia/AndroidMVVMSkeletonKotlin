package com.androidskeleton.mvvm.module.main


import com.androidskeleton.mvvm.di.scope.FragmentScoped

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainModule {
    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun mainFragment(): MainFragment
}
