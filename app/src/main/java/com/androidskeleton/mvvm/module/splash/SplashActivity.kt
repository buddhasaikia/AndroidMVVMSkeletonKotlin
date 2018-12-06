package com.androidskeleton.mvvm.module.splash

import android.app.Activity
import android.content.Intent
import android.os.Bundle

import com.androidskeleton.mvvm.module.main.MainActivity


class SplashActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this@SplashActivity, MainActivity::class.java)
        startActivity(intent)
        this.finish()
    }
}
