package com.androidskeleton.mvvm.data.datasource


import com.androidskeleton.mvvm.data.api.ApiService
import com.androidskeleton.mvvm.data.datasource.base.BaseDataSource
import com.androidskeleton.mvvm.data.datasource.base.DataSource

import io.reactivex.Observable

/**
 * Created by Buddha Saikia on 06-10-2018.
 */

class RemoteDataSource(private val apiService: ApiService) :
        BaseDataSource(), DataSource.Greetings {

    override fun greetings(): Observable<String>? {
        return Observable.just("Greetings from API sample")
                .compose(this.applySchedulersIO())
    }
}
