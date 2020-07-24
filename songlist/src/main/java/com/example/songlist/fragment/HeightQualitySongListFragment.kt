package com.example.songlist.fragment

import android.os.Bundle
import android.view.FrameStats
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.conmon.base.AVFragment
import com.example.conmon.extension.lifecycleObserve
import com.example.songlist.R
import com.example.songlist.adapter.FragmentCreator
import com.example.songlist.di.songListModel
import com.example.songlist.di.songSquareModule
import com.example.songlist.vm.SongListViewModel
import kotlinx.android.synthetic.main.fragment_song_square.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.AndroidLifecycleScope
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.scoped
import org.kodein.di.generic.singleton

class HeightQualitySongListFragment(override var mCat: String) : AVFragment<SongListViewModel>(),
    KodeinAware, FragmentCreator {
    private lateinit var recyclerView: RecyclerView
    override val viewModel: SongListViewModel by instance<SongListViewModel>()
    override val kodein = Kodein.lazy {
        extend(parent)
        import(songListModel)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_song_square, container, false)
        initView(view)
        bindViewData()
        return view
    }


    private fun initView(view: View) {
        recyclerView = f(R.id.song_square_recycler_view, view)
    }

    private fun initData() {
        viewModel.getHeightQualitySongList()
    }

    private fun bindViewData() {
        viewModel.heightQualitySongList.lifecycleObserve(lifeCycleProvide, Observer {

        })
    }


    override fun createFragment(): Fragment {
        return this
    }

    private fun <T : View> f(id: Int, view: View): T {
        return view.findViewById(id)
    }
}