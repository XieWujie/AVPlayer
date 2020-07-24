package com.example.conmon.base

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.lifecycle.MutableLiveData
import org.kodein.di.android.closestKodein

abstract class AVActivity<R : AVViewModel<*>> : AppCompatActivity(), ErrorAware {

    protected val parent by closestKodein()
    protected val lifeCycleProvide = AndroidLifeCycleProvide(this)
    abstract val viewModel: R

    override val error: MutableLiveData<Throwable> by lazy { viewModel.error }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
//        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//        window.decorView.systemUiVisibility =
//            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//        window.statusBarColor = Color.TRANSPARENT

    }


    protected fun fitStateStyle(colorId: Int) {
        val decorView = window.decorView as ViewGroup
        val color = resources.getColor(colorId)
//        decorView.getChildAt(0).background = ColorDrawable(color)
        window.statusBarColor = color
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            var flag = if(color == Color.WHITE){
                decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }else{
                decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
            }
            decorView.systemUiVisibility = flag
        }
    }
}