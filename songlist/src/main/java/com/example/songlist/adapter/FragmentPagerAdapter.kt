package com.example.songlist.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager.widget.PagerAdapter

class FragmentPagerAdapter(
    private var mList: List<FragmentCreator>,
    private val fragmentManager: FragmentManager
) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    //使用FragmentPagerAdapter 避免页面销毁而重新加载数据
    //使用FragmentStatePagerAdapter 避免内存占用过大，但每次都需重新加载数据


    override fun getItem(position: Int): Fragment {
        val fragmentCreator = mList[position]
        return fragmentCreator.createFragment()
    }

    override fun getCount() = mList.size

    override fun getItemPosition(`object`: Any): Int {
        return PagerAdapter.POSITION_NONE
    }

    fun setFragments(list: List<FragmentCreator>) {
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        for (f in fragmentManager.fragments) {
            transaction.remove(f)
        }
        transaction.commitNow()
        mList = list
        notifyDataSetChanged()
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mList[position].mCat
    }

}

interface FragmentCreator {
    var mCat: String

    fun createFragment(): Fragment
}