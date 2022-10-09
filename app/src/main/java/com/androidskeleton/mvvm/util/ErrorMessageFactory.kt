package com.androidskeleton.mvvm.util

import android.accounts.NetworkErrorException

import java.net.ConnectException
import java.net.NoRouteToHostException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class ErrorMessageFactory {

    fun getError(t: Throwable): String {
        t.printStackTrace()
        return when (t) {
            is ConnectException, is UnknownHostException, is NetworkErrorException -> {
                "Could not connect to sever, Please check your internet connection or try again later."
            }
            is SocketTimeoutException, is NoRouteToHostException -> {
                "Something snapped. Please check internet connection and try again."
            }
            else -> {
                "Something went wrong. Please try again later."
            }
        }
    }
}
