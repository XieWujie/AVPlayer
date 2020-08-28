package com.example.songlist.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.common.base.AVFragment
import com.example.songlist.R
import com.example.songlist.adapter.FragmentCreator
import com.example.songlist.vm.SongListViewModel
import com.xie.di.AutoWire
import com.xie.di.Service

class HeightQualitySongListFragment @Service constructor(override var mCat: String) : AVFragment(), FragmentCreator {
    private lateinit var recyclerView: RecyclerView
    @AutoWire
    lateinit var viewModel: SongListViewModel


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
//        viewModel.heightQualitySongList.lifecycleObserve(DiBus.lifeCycle(this), Observer {
//
//        })
    }


    override fun createFragment(): Fragment {
        return this
    }

    private fun <T : View> f(id: Int, view: View): T {
        return view.findViewById(id)
    }
}