package com.androidskeleton.mvvm.data.repository

import com.androidskeleton.mvvm.data.datasource.LocalDataSource
import com.androidskeleton.mvvm.data.datasource.RemoteDataSource
import com.androidskeleton.mvvm.data.datasource.base.BaseDataSource
import com.androidskeleton.mvvm.data.datasource.base.DataSource

import javax.inject.Inject
import javax.inject.Singleton

import io.reactivex.Observable

import dagger.internal.Preconditions.checkNotNull

/**
 * Created by Buddha Saikia on 06-10-2018.
 */
@Singleton
class Repository @Inject
constructor(remoteDataSource: RemoteDataSource, localDataSource: LocalDataSource) :
        BaseDataSource(), DataSource.Greetings {

    private val remoteDataSource: RemoteDataSource = checkNotNull(remoteDataSource)
    private val localDataSource: LocalDataSource = checkNotNull(localDataSource)

    override fun greetings(): Observable<String>? {
        return remoteDataSource.greetings()
    }
}
