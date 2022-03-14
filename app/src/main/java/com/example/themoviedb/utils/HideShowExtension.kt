package com.example.themoviedb.utils

import android.os.Build
import android.view.View
import android.widget.ImageView
import androidx.annotation.RequiresApi
import com.example.themoviedb.R
import com.example.themoviedb.app.App

/**
 * Created by Botirali Kozimov on 10-03-21
 **/

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.enable() {
    this.isEnabled = true
}

fun View.disable() {
    this.isEnabled = false
}

@RequiresApi(Build.VERSION_CODES.M)
fun ImageView.disableNext() {
    this.isEnabled = false
    this.setBackgroundColor(App.instance.getColor(R.color.colorGrey))
}

@RequiresApi(Build.VERSION_CODES.M)
fun ImageView.enableNext() {
    this.isEnabled = true
    this.setBackgroundColor(App.instance.getColor(R.color.blue))
}


fun View.disableWithAlpha() {
    this.isEnabled = false
    this.alpha = 0.5f
}

fun View.enableWithAlpha() {
    this.isEnabled = true
    this.alpha = 1f
}