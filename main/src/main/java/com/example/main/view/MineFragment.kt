package com.example.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dibus.AndroidLifeCycleProvide
import com.example.common.Account
import com.example.common.base.AVFragment
import com.example.common.extension.bind
import com.example.common.extension.toast
import com.example.main.R
import com.example.main.adapter.mine.MyMusicAdapter
import com.example.main.adapter.mine.SongListAdapter
import com.example.main.databinding.FragmentMineBinding
import com.example.main.viewmodel.MineViewModel
import com.dibus.AutoWire
import com.dibus.DiBus
import com.dibus.Scope
import dibus.main.MineFragmentCreator

/**
 * 主页下我的 tab下的UI
 */
internal const val MINE_FRAGMENT_SCOPE = "mine_fragment_scope"
class MineFragment :AVFragment(){


    private lateinit var binding:FragmentMineBinding
    @AutoWire
    lateinit var viewModel:MineViewModel
    private val songListAdapter = SongListAdapter()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        DiBus.registerScope(MINE_FRAGMENT_SCOPE,AndroidLifeCycleProvide(this))
        MineFragmentCreator.inject(this)
        binding = inflater.bind(R.layout.fragment_mine,container)
        dispatchEvent()
        initView()
        return binding.root
    }



    private fun initView(){
        Glide.with(this).load(Account.avatarUrl).into(binding.userAvatarView)
        binding.username.text = Account.nickname
        val myMusic = binding.myMusicRecycleview
        myMusic.layoutManager = LinearLayoutManager(context,RecyclerView.HORIZONTAL,false)
        myMusic.adapter = MyMusicAdapter()

        val playListView = binding.songListRecyclerview
        playListView.layoutManager = GridLayoutManager(context,2)
        playListView.adapter = songListAdapter
    }

    private fun dispatchEvent(){

        viewModel.subCount().observe(viewLifecycleOwner, Observer {
            binding.createSongListCountText.text = "${it.djRadioCount}"
            binding.collectionSongListCountTextview.text = "${it.subPlaylistCount}"
        })
        viewModel.played().observe(viewLifecycleOwner, Observer {
            binding.playedCountTextview.text = "${it.weekData.size}"
            if(it.weekData.isNotEmpty()) {
                Glide.with(this).load(it.weekData[0].song.al.picUrl).into(binding.latestSongImgView)
            }
        })
        viewModel.playedList().registerLifeCycle(AndroidLifeCycleProvide(this))
            .doOnError { context?.toast(it.message?:"歌单请求错误") }
            .doOnComplete {
                songListAdapter.setList(it)
            }.post()
    }

}