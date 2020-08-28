package com.example.songlist.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.common.base.AVFragment
import com.example.common.extension.lifecycleObserve
import com.example.songlist.R
import com.example.songlist.adapter.AllSongListAdapter
import com.example.songlist.adapter.FragmentCreator
import com.example.songlist.bean.Playlists
import com.example.songlist.vm.SongListViewModel
import com.xie.di.AutoWire
import com.xie.di.DiBus

class SongListFragment : AVFragment(),
    FragmentCreator {
    @AutoWire
    lateinit var viewModel: SongListViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: AllSongListAdapter
    private lateinit var sourceList: ArrayList<Playlists>




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
        viewModel.getSongList(cat = mCat)
        Log.e(TAG, mCat)
    }

    private fun bindViewData() {
        sourceList = ArrayList()
        recyclerAdapter = AllSongListAdapter(R.layout.item_all_song_list, sourceList)
        viewModel.songList.lifecycleObserve(DiBus.lifeCycle(this), Observer {
            Log.e(TAG, it.toString())
            sourceList.addAll(it)
            recyclerAdapter.addData(sourceList)
        })
        recyclerView.layoutManager = GridLayoutManager(activity, 3)
        recyclerView.adapter = recyclerAdapter

    }

    override var mCat: String = ""

    fun setCat(cat: String): FragmentCreator {
        mCat = cat
        return this
    }


    override fun createFragment(): Fragment {
        return this
    }

    private fun <T : View> f(id: Int, view: View): T {
        return view.findViewById(id)
    }

    private val TAG = this.javaClass.simpleName
}