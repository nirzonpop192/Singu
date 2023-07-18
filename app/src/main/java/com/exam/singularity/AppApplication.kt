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

    }

    override fun onCreate() {
        super.onCreate()
        headContext = this.applicationContext

    }
}