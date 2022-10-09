package com.androidskeleton.mvvm.ui.main


import android.os.Bundle

import com.androidskeleton.mvvm.R
import com.androidskeleton.mvvm.ui.base.DaggerBaseActivity
import com.androidskeleton.mvvm.util.ActivityUtils

class MainActivity : DaggerBaseActivity() {


    override val layoutId: Int
        get() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mainFragment = MainFragment.newInstance()
        ActivityUtils.addFragmentToActivity(supportFragmentManager, mainFragment, R.id.container)
    }
}
