package com.example.themoviedb.app

import android.app.Application
import com.example.themoviedb.data.local.LocalStorage
import com.intuit.sdp.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * Created by Jasurbek Kurganbayev 13/03/2022
 */

@HiltAndroidApp
class App : Application() {


    override fun onCreate() {
        super.onCreate()
        instance = this
        LocalStorage.init(this)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    companion object {

        lateinit var instance: App
    }
}