package com.example.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.example.common.ACCOUNT
import com.example.common.Account
import com.example.common.PASSWORD
import com.example.common.base.AVActivity
import com.example.main.adapter.mine.PageTabAdapter
import com.example.main.databinding.ActivityMainBinding
import com.example.main.view.MainAppBar
import com.example.main.view.PanDrawable
import com.example.main.viewmodel.MainViewModel
import com.example.route.AVRoute
import com.example.route.annotation.Route
import com.xie.di.AutoWire

@Route("main/main")
class MainActivity : AVActivity() {


    @AutoWire
     lateinit var viewModel: MainViewModel
    @AutoWire
    lateinit var mainTapAdapter:PageTabAdapter

    private lateinit var binding:ActivityMainBinding

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
        decorView.background = drawable
        binding.mainPage.registerOnPageChangeCallback(object :ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
              when(position){
                  0->{
                      fitStateStyle(resources.getColor(com.example.common.R.color.bg_black),STATE_STYLE_DARK)
                      binding.mainAppBar.setStyle(MainAppBar.Style.BLACK)
                  }
                  else->{
                      drawable.updateX(1f)
                      decorView.background = drawable
                      fitStateStyle(resources.getColor(com.example.common.R.color.bg_white),STATE_STYLE_LIGHT)
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
