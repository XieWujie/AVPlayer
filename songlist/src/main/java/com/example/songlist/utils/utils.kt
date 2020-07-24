package com.example.songlist.utils

import android.graphics.Color
import android.os.Build
import android.view.View
import androidx.appcompat.app.AppCompatActivity

fun setBarDarkTransparent(context: AppCompatActivity) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val systemUiVisibility = context.window.decorView.systemUiVisibility
        context.window.decorView.systemUiVisibility =
            systemUiVisibility and (View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()) or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        context.window.statusBarColor = Color.TRANSPARENT
    }
}

fun setBarLightTransparent(context: AppCompatActivity) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val systemUiVisibility = context.window.decorView.systemUiVisibility
        context.window.decorView.systemUiVisibility =
            systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        context.window.statusBarColor = Color.TRANSPARENT
    }
}

fun setBarLight(context: AppCompatActivity) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val systemUiVisibility = context.window.decorView.systemUiVisibility
        context.window.decorView.systemUiVisibility =
            systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }
}

fun setBarDark(context: AppCompatActivity) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val systemUiVisibility = context.window.decorView.systemUiVisibility
        context.window.decorView.systemUiVisibility =
            systemUiVisibility and (View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv())
    }
}