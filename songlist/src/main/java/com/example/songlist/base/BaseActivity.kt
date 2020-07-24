//package com.example.songlist.base
//
//import android.graphics.Color
//import android.os.Build
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import androidx.annotation.LayoutRes
//import androidx.appcompat.app.AppCompatActivity
//import androidx.appcompat.widget.Toolbar
//import com.example.conmon.base.AVActivity
//import com.example.conmon.base.AVViewModel
//import kotlin.properties.Delegates
//import com.example.songlist.R
//import com.example.songlist.view.ToolbarLayout
//import com.google.android.material.tabs.TabLayout
//import kotlinx.android.synthetic.main.activity_base.*
//
////<R : AVViewModel<*>> : AVActivity<R>()
//abstract class BaseActivity : AppCompatActivity() {
//    companion object {
//        private const val STATUS_BAR_NONE = -1
//        private const val STATUS_BAR_LIGHT = 0
//        private const val STATUS_BAR_DARK = 1
//    }
//
//    protected var toolbar: ToolbarLayout? = null;
//
//    private var mode by Delegates.observable(STATUS_BAR_NONE) { _, old, new ->
//        if (old != new) {
//            setStatusBar(new)
//        }
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setStatusBarMode(true)
//        when {
//            getLayoutId() == 0 -> setContentView()
//
//            !showToolBar() -> setContentView(getLayoutId())
//            transparentStatusBar() -> setContentView(getLayoutId())
//
//            else -> {
//                setContentView(R.layout.activity_base)
//                View.inflate(this, getLayoutId(), activityRootView)
//            }
//        }
//
//        toolbar = f(R.id.baseToolbar)
//        toolbar?.apply {
//
//        }
//
//    }
//
//
//    /**
//     * 设置状态栏样式
//     */
//    private fun setStatusBar(mode: Int) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            val systemUiVisibility = window.decorView.systemUiVisibility
//            if (transparentStatusBar()) {
//                window.decorView.systemUiVisibility = when (mode) {
//                    STATUS_BAR_LIGHT -> systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    STATUS_BAR_DARK -> systemUiVisibility and (View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()) or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    else -> systemUiVisibility
//                }
//            } else {
//                window.decorView.systemUiVisibility = when (mode) {
//                    STATUS_BAR_LIGHT -> systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
//                    STATUS_BAR_DARK -> systemUiVisibility and (View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv())
//                    else -> systemUiVisibility
//                }
//            }
//            window.statusBarColor = Color.TRANSPARENT
//        }
//
//
//    }
//
//
//
//    fun setStatusBarMode(isLight: Boolean) {
//        if (isFullScream()) {
//            return
//        }
//        mode = if (isLight) {
//            STATUS_BAR_LIGHT
//        } else {
//            STATUS_BAR_DARK
//        }
//
//    }
//
//    /**
//     * 设置是否透明状态栏
//     */
//    open fun transparentStatusBar(): Boolean = false
//
//    /**
//     * 设置是否全屏
//     */
//    open fun isFullScream(): Boolean = false
//
//    /**
//     * 设置是否显示ToolBar
//     */
//    open fun showToolBar(): Boolean = true
//
//    /**
//     * 获取LayoutId
//     */
//    @LayoutRes
//    abstract fun getLayoutId(): Int
//
//    open fun setContentView() {
//
//    }
//
//    /**
//     * 初始化视图
//     */
//    open fun initView() {
//
//    }
//
//    /**
//     * 初始化数据
//     */
//    open fun initData() {
//
//    }
//
//    fun <T : View> f(id: Int): T? {
//        return findViewById<T>(id)
//    }
//
//}