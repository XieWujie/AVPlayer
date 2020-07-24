package com.example.songlist.adapter

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.*
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.songlist.fragment.SongListFragment

class FragmentPagerAdapter(
    private var mList: List<FragmentCreator>,
    private var catList: List<String>,
    private val lifecycle: Lifecycle,
    private val fragmentManager: FragmentManager
) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    //使用FragmentPagerAdapter 避免页面销毁而重新加载数据
    //使用FragmentStatePagerAdapter 避免内存占用过大，但每次都需重新加载数据
    private var currentPosition: Int = 0

    fun setFragments(list: List<FragmentCreator>) {
        mList = list
    }

    fun setCats(list: List<String>) {
        catList = list
    }


    override fun getItemCount(): Int {
        return mList.size
    }

    override fun createFragment(position: Int): Fragment {
        Log.e(TAG,"createFragment $position")
        return mList[position].createFragment(catList[position])
    }


    private val TAG = this.javaClass.simpleName

}

interface FragmentCreator {
    var mCat: String

    fun createFragment(cat: String): Fragment
}