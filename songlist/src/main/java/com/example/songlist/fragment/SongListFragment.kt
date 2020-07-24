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
import com.example.conmon.base.AVFragment
import com.example.conmon.extension.lifecycleObserve
import com.example.songlist.R
import com.example.songlist.adapter.AllSongListAdapter
import com.example.songlist.adapter.FragmentCreator
import com.example.songlist.bean.Playlists
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

class SongListFragment() : AVFragment<SongListViewModel>(), KodeinAware,
    FragmentCreator {
    override val viewModel: SongListViewModel by instance<SongListViewModel>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: AllSongListAdapter
    private lateinit var sourceList: ArrayList<Playlists>


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

    public fun initData() {
        viewModel.getSongList(cat = mCat)
        Log.e(TAG, mCat)
    }

    private fun bindViewData() {
        sourceList = ArrayList()
        recyclerAdapter = AllSongListAdapter(R.layout.item_all_song_list, sourceList)
        viewModel.songList.lifecycleObserve(lifeCycleProvide, Observer {
            sourceList.clear()
            sourceList.addAll(it)
            recyclerAdapter.setData(sourceList)
        })
        recyclerView.layoutManager = GridLayoutManager(activity, 3)
        recyclerView.adapter = recyclerAdapter
    }

    override var mCat: String = ""

    fun setCat(cat: String): FragmentCreator {
        mCat = cat
        return this
    }


    override fun createFragment(cat: String): Fragment {
        return SongListFragment().setCat(cat) as Fragment
    }

    private fun <T : View> f(id: Int, view: View): T {
        return view.findViewById(id)
    }

    private val TAG = this.javaClass.simpleName
}
