package com.example.main.view

import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.conmon.base.AVFragment
import com.example.conmon.extension.bind
import com.example.conmon.extension.toast
import com.example.main.R
import com.example.main.databinding.FragmentMineBinding
import com.example.main.di.mine_fragment
import com.example.main.http.entry.Song
import com.example.main.http.entry.SubCountEntry
import com.example.main.viewmodel.MineViewModel
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.AndroidLifecycleScope
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.scoped
import org.kodein.di.generic.singleton

/**
 * 主页下我的 tab下的UI
 */
class MineFragment :AVFragment(),KodeinAware{

    override val kodein = Kodein.lazy {
        extend(parent)
        import(mine_fragment)
        bind<MineFragment>() with scoped(AndroidLifecycleScope).singleton{ this@MineFragment }
    }

    private val viewModel:MineViewModel by instance()
    private lateinit var binding:FragmentMineBinding
    private lateinit var error:MutableLiveData<Exception>
    private lateinit var subCount:MutableLiveData<SubCountEntry>
    private lateinit var latestSong:MutableLiveData<Song?>
    private lateinit var playedCount:MutableLiveData<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LayoutInflater.from(context).bind(R.layout.fragment_mine,null)
        error = MutableLiveData()
        subCount = MutableLiveData()
        latestSong = MutableLiveData()
        playedCount = MutableLiveData()

    }

    private fun registerEvent(){
        viewModel.register(error)
        viewModel.resentPlay(playedCount,latestSong)
        viewModel.loadSubCount(subCount)
    }

    private fun dispatchEvent(){
        error.observe(this, Observer {
            context?.toast(it.message?:"")
        })
        subCount.observe(this, Observer {
            binding.createSongListCountText.text = "${it.createDjRadioCount}"
            binding.collectionSongListCountTextview.text = "${it.subPlaylistCount}"
        })

        latestSong.observe(this, Observer {

        })
        playedCount.observe(this, Observer {

        })
    }

}