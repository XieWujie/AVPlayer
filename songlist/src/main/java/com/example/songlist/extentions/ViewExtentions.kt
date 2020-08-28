package com.example.songlist.extentions

import android.content.Context
import android.content.res.Resources
import android.os.Build
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout

fun View.visible() {
    if (visibility != View.VISIBLE) {
        visibility = View.VISIBLE
    }
}

fun View.gone() {
    if (visibility != View.GONE) {
        visibility = View.GONE
    }
}

fun View.visibility(isVisible: Boolean) {
    if (isVisible) {
        visible()
    } else {
        gone()
    }
}

fun TabLayout.setText(text: CharSequence) {
    this.addTab(this.newTab().setText(text))
}

fun getResourceColor(context: Context, id: Int, theme: Resources.Theme? = null): Int {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        context.resources.getColor(id, theme)
    } else {
        context.resources.getColor(id)
    }
}

fun ImageView.load(url: Any) {
    Glide.with(this).load(url).into(this)
}
