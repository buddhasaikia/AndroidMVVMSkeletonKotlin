package com.androidskeleton.mvvm.data.datasource.base


import io.reactivex.Observable

/**
 * Created by Buddha Saikia on 06-10-2018.
 */

interface DataSource {

    interface Greetings {
        fun greetings(): Observable<String>?
    }

}
