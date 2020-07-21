package com.example.main

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.Shape
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.example.conmon.ACCOUNT
import com.example.conmon.Account
import com.example.conmon.PASSWORD
import com.example.conmon.base.AVActivity
import com.example.main.adapter.PageTabAdapter
import com.example.main.databinding.ActivityMainBinding
import com.example.main.di.MAIN_ACTIVITY_MODULE
import com.example.main.view.MainAppBar
import com.example.main.view.PanDrawable
import com.example.main.viewmodel.MainViewModel
import com.example.route.AVRoute
import com.example.route.annotation.Route
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.AndroidLifecycleScope
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.scoped
import org.kodein.di.generic.singleton

@Route("main/main")
class MainActivity : AVActivity<MainViewModel>(),KodeinAware {

    override val kodein = Kodein.lazy {
        extend(parent)
        import(MAIN_ACTIVITY_MODULE)
        bind<MainActivity>() with scoped(AndroidLifecycleScope).singleton {
            this@MainActivity
        }
    }
    override val viewModel: MainViewModel by instance()
    val mainTapAdapter by instance<PageTabAdapter>()
    lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        checkAccount()
        initView()
        dispatchEvent()
    }

    private fun initView(){
        binding.mainPage.adapter = mainTapAdapter
        binding.mainAppBar.dispatchPageEvent(binding.mainPage)
    }

    private fun dispatchEvent(){
        val drawable = PanDrawable()
        val decorView = window.decorView
        binding.mainPage.registerOnPageChangeCallback(object :ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
              when(position){
                  0->{
                      fitStateStyle(com.example.common.R.color.bg_black)
                      binding.mainAppBar.setStyle(MainAppBar.Style.BLACK)
                  }
                  else->{
                      drawable.updateX(1f)
                      decorView.background = drawable
                      fitStateStyle(com.example.common.R.color.bg_white)
                      binding.mainAppBar.setStyle(MainAppBar.Style.WHILE)
                  }
              }
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int
            ) {
                if(position == 0){
                    drawable.updateX(positionOffset)
                    decorView.background = drawable
                }
            }
        })
    }


    private fun checkAccount(){
        val uid = Account.uid
        if(uid == 0){
            AVRoute().route("login/cellphone",this){
                ACCOUNT with "13677626587"
                PASSWORD with "wyy123456"
            }.execute()
        }
    }
}
