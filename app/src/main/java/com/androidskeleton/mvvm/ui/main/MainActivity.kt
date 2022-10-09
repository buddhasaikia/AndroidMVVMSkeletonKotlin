package com.androidskeleton.mvvm.ui.main


import android.view.LayoutInflater
import com.androidskeleton.mvvm.R
import com.androidskeleton.mvvm.databinding.ActivityMainBinding
import com.androidskeleton.mvvm.ui.base.DaggerBaseActivity
import com.androidskeleton.mvvm.util.ActivityUtils

class MainActivity : DaggerBaseActivity<ActivityMainBinding>() {

    override fun initUI() {
        val mainFragment = MainFragment.newInstance()
        ActivityUtils.addFragmentToActivity(supportFragmentManager, mainFragment, R.id.container)
    }

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding = ActivityMainBinding::inflate
}
