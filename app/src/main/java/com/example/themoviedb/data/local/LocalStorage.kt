package com.example.themoviedb.data.local

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by Jasurbek Kurganbayev 13/03/2022
 */
class LocalStorage private constructor(context: Context) {

    companion object {

        @Volatile
        lateinit var instance: LocalStorage
            private set

        fun init(context: Context) {
            synchronized(this) {
                instance = LocalStorage(context)
            }
        }
    }

    val pref: SharedPreferences =
        context.getSharedPreferences("LocalStorage", Context.MODE_PRIVATE)


}