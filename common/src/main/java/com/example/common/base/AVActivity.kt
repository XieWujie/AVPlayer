package com.example.common.base

import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import org.kodein.di.android.closestKodein


abstract class AVActivity<R : AVViewModel<*>> : AppCompatActivity(), ErrorAware {

    protected val parent by closestKodein()
    protected val lifeCycleProvide = AndroidLifeCycleProvide(this)
    protected val STATE_STYLE_LIGHT = 1
    protected val STATE_STYLE_DARK = 0
    abstract val viewModel: R

    override val error: MutableLiveData<Throwable> by lazy { viewModel.error }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
 //       window.decorView.systemUiVisibility =  window.decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
//        window.decorView.systemUiVisibility =
//            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//        window.statusBarColor = Color.TRANSPARENT
    }


    protected fun fitStateStyle(color: Int,style:Int = STATE_STYLE_LIGHT) {
        val decorView = window.decorView as ViewGroup
        window.statusBarColor = color
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
           if(style == STATE_STYLE_LIGHT){
                decorView.systemUiVisibility =  decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }else if(style == STATE_STYLE_DARK){
                decorView.systemUiVisibility =  decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
            }
        }
    }
}