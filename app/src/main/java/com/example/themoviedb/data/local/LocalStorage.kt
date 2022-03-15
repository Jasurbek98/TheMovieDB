package com.example.themoviedb.data.local

import android.content.Context
import android.content.SharedPreferences
import com.example.themoviedb.utils.helpers.IntPreference
import com.example.themoviedb.utils.helpers.StringPreference

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

    var screenState: String by StringPreference(pref)

    var movieId: Int by IntPreference(pref, -1)
    var actorId: Int by IntPreference(pref, -1)


}