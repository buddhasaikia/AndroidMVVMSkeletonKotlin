package com.androidskeleton.mvvm.di.components

import android.app.Application

import com.androidskeleton.mvvm.di.module.ActivityBindingModule
import com.androidskeleton.mvvm.di.module.AppModule
import com.androidskeleton.mvvm.di.module.RepositoryModule

import javax.inject.Singleton

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule

/**
 * Created by Buddha Saikia on 06-10-2018.
 */

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    ActivityBindingModule::class, RepositoryModule::class
])
interface AppComponent : AndroidInjector<DaggerApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
