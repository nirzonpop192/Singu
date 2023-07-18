package com.exam.singularity

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AppApplication : Application() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        // need to check internet from any where with out activityFragment
        var headContext: Context? = null
//        var isBackground = true
//        var isLoggedIn = false
//        var prefs: Prefs? = null

//        @SuppressLint("StaticFieldLeak")
//        lateinit var instance: MyApplication
//            private set
    }

    override fun onCreate() {
        super.onCreate()
        headContext = this.applicationContext
//        instance = this
//        prefs = Prefs(applicationContext)

    }
}