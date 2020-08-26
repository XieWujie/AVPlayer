package com.example.songlist

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import com.example.common.base.AVActivity
import com.example.common.extension.lifecycleObserve
import com.example.route.annotation.Route
import com.example.songlist.adapter.FragmentCreator
import com.example.songlist.adapter.FragmentPagerAdapter
import com.example.songlist.extentions.getResourceColor
import com.example.songlist.extentions.setText
import com.example.songlist.fragment.HeightQualitySongListFragment
import com.example.songlist.fragment.SongListFragment
import com.example.songlist.utils.setBarLightTransparent
import com.example.songlist.view.ToolbarLayout
import com.example.songlist.vm.SongSquareViewModel
import com.google.android.material.tabs.TabLayout
import com.xie.di.AutoWire
import com.xie.di.BusEvent
import com.xie.di.DiBus
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.AndroidLifecycleScope
import org.kodein.di.generic.*

@Route("songlist/songsquare")
class SongSquareActivity : AVActivity() {

    private lateinit var toolbarLayout: ToolbarLayout
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager
    private lateinit var fragmentList: ArrayList<FragmentCreator>
    private lateinit var pagerAdapter: FragmentPagerAdapter

    @AutoWire
    lateinit var viewModel: SongSquareViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBarLightTransparent(this)
        setContentView(R.layout.activity_song_square)
        initView()
        initData()
        bindViewData()
    }

    private fun initView() {
        toolbarLayout = f(R.id.song_square_toolbar)
        toolbarLayout.setBackClickListener { onBackPressed() }
        tabLayout = f(R.id.song_square_tablayout)
        viewPager = f(R.id.song_square_viewpager)
    }

    private fun initData() {
        viewModel.getSongCategoryList()
        fragmentList = ArrayList<FragmentCreator>()
        pagerAdapter = FragmentPagerAdapter(fragmentList, supportFragmentManager)
    }

    private fun bindViewData() {
        bindTabLayout()
        bindViewPager()
    }

    private fun bindTabLayout() {
        tabLayout.setText("全部")

        fragmentList.add(SongListFragment().setCat("全部歌单"))
        tabLayout.setText("精品")
        fragmentList.add(HeightQualitySongListFragment("精品"))
        viewModel.songCategorys.lifecycleObserve(
            DiBus.lifeCycle(this),
            Observer {
                Log.e(TAG, it.toString())
                it.forEach { sub ->
                    tabLayout.setText(sub.name)
                    fragmentList.add(SongListFragment().setCat(sub.name))
                }
                pagerAdapter.setFragments(fragmentList)
                tabLayout.setupWithViewPager(viewPager)
            })
        Log.e(TAG, fragmentList.size.toString())

        tabLayout.setTabTextColors(
            getResourceColor(this, R.color.textColor),
            getResourceColor(this, R.color.tabSelectColor)
        )

    }

    private fun bindViewPager() {
        viewPager.adapter = pagerAdapter
        viewPager.currentItem = 0

    }

    private fun <T : View> f(id: Int): T {
        return findViewById(id)
    }

    private val TAG = this.javaClass.simpleName
}
