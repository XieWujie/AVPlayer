package com.example.main.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.widget.Toolbar
import androidx.viewpager2.widget.ViewPager2
import com.example.conmon.extension.bind
import com.example.main.R
import com.example.main.databinding.MainAppBarLayoutBinding

class MainAppBar:Toolbar{

    lateinit var binding:MainAppBarLayoutBinding

    constructor(context: Context):super(context){
        init()
    }

    constructor(context: Context,attributeSet: AttributeSet):super(context,attributeSet){
        init()
    }

    private fun init(){
        binding =  LayoutInflater.from(context).bind(R.layout.main_app_bar_layout,this)
        addView(binding.root)
    }

    fun setStyle(style:Style){
        when(style){
            Style.WHILE->{
                binding.apply {
                    val color = resources.getColor(com.example.common.R.color.font_black)
                    val b = resources.getColor(com.example.common.R.color.bg_white)
                    mineTabView.setTextColor(color)
                    findTabView.setTextColor(color)
                    cloudTabView.setTextColor(color)
                    video.setTextColor(color)
                    menuTabView.setImageResource(R.drawable.ic_menu_black)
                    icSeaechView.setImageResource(R.drawable.ic_menu_black)
                }
            }
            Style.BLACK->{
                binding.apply {
                    val color = resources.getColor(com.example.common.R.color.font_while)
                    val b = resources.getColor(com.example.common.R.color.bg_black)
                    mineTabView.setTextColor(color)
                    findTabView.setTextColor(color)
                    cloudTabView.setTextColor(color)
                    video.setTextColor(color)
                    menuTabView.setImageResource(R.drawable.ic_menu_while)
                    icSeaechView.setImageResource(R.drawable.ic_menu_while)
                }
            }
        }
    }


    fun dispatchPageEvent(pager2: ViewPager2){
        binding.mineTabView.setOnClickListener { pager2.currentItem = 0 }
        binding.findTabView.setOnClickListener { pager2.currentItem = 1 }
        binding.cloudTabView.setOnClickListener { pager2.currentItem = 2 }
        binding.video.setOnClickListener { pager2.currentItem = 3 }
    }

    companion object{


    }

    sealed class Style{
        class White:Style()
        class Black:Style()
        companion object{
            val WHILE = White()
            val BLACK = Black()
        }
    }
}