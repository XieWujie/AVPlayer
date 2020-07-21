package com.example.main.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class PageTabAdapter(fragmentActivity: FragmentActivity,val list:List<Fragment>) :FragmentStateAdapter(fragmentActivity){
    override fun getItemCount(): Int {
        return list.size
    }

    override fun createFragment(position: Int): Fragment {
        return list[position]
    }


}