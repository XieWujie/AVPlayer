package com.example.songlist

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.conmon.base.AVActivity
import com.example.conmon.base.AndroidLifeCycleProvide
import com.example.conmon.extension.lifecycleObserve
import com.example.route.annotation.Route
import com.example.songlist.adapter.FragmentCreator
import com.example.songlist.adapter.FragmentPagerAdapter
import com.example.songlist.di.songSquareModule
import com.example.songlist.extentions.getResourceColor
import com.example.songlist.extentions.setText
import com.example.songlist.fragment.HeightQualitySongListFragment
import com.example.songlist.fragment.SongListFragment
import com.example.songlist.utils.setBarLightTransparent
import com.example.songlist.view.ToolbarLayout
import com.example.songlist.vm.SongSquareViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.AndroidLifecycleScope
import org.kodein.di.generic.*
import org.kodein.di.newInstance

/**
 * 剩余工作：
 * ① 设置“正在加载”动画
 *
 */

@Route("songlist/songsquare")
class SongSquareActivity : AVActivity<SongSquareViewModel>(), KodeinAware {
    private lateinit var toolbarLayout: ToolbarLayout
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var fragmentList: ArrayList<FragmentCreator>
    private lateinit var pagerAdapter: FragmentPagerAdapter
    private lateinit var catList: ArrayList<String>

    override val viewModel: SongSquareViewModel by instance<SongSquareViewModel>()

    override val kodein: Kodein = Kodein.lazy {
        extend(parent)
        import(songSquareModule)
        bind<SongSquareActivity>() with scoped(AndroidLifecycleScope).singleton { this@SongSquareActivity }
    }

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
        catList = ArrayList()
        pagerAdapter =
            FragmentPagerAdapter(fragmentList, catList, lifecycle, supportFragmentManager)
    }

    private fun bindViewData() {
        bindTabLayout()
        bindViewPager()
    }

    private fun bindTabLayout() {
        tabLayout.setText("全部")
        catList.add("全部")
        fragmentList.add(SongListFragment().setCat("全部歌单"))
//        tabLayout.setText("精品")
//        catList.add("精品")
//        fragmentList.add(HeightQualitySongListFragment("精品"))
        viewModel.songCategorys.lifecycleObserve(
            lifeCycleProvide,
            Observer {
                Log.e(TAG, it.toString())
                it.forEach { sub ->
                    tabLayout.setText(sub.name)
                    catList.add(sub.name)
                    fragmentList.add(SongListFragment().setCat(sub.name))
                }
                pagerAdapter.setCats(catList)
                pagerAdapter.setFragments(fragmentList)
                TabLayoutMediator(
                    tabLayout,
                    viewPager,
                    TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                        tab.text = catList[position]
                    }).attach()
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
