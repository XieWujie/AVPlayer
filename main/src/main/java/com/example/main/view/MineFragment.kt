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
import com.example.conmon.Account
import com.example.conmon.base.AVFragment
import com.example.conmon.extension.bind
import com.example.conmon.extension.toast
import com.example.main.R
import com.example.main.adapter.mine.MyMusicAdapter
import com.example.main.adapter.mine.SongListAdapter
import com.example.main.databinding.FragmentMineBinding
import com.example.main.di.mine_fragment
import com.example.main.viewmodel.MineViewModel
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

/**
 * 主页下我的 tab下的UI
 */
class MineFragment :AVFragment<MineViewModel>(),KodeinAware{

    override val kodein = Kodein.lazy {
        extend(parent)
        import(mine_fragment)
    }
    private lateinit var binding:FragmentMineBinding
    override val viewModel:MineViewModel by instance()
    private val songListAdapter by instance<SongListAdapter>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
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
        error.observe(this, Observer {
            context?.toast(it.message?:"")
        })
        viewModel.subCount().observe(this, Observer {
            binding.createSongListCountText.text = "${it.djRadioCount}"
            binding.collectionSongListCountTextview.text = "${it.subPlaylistCount}"
        })
        viewModel.played().observe(this, Observer {
            binding.playedCountTextview.text = "${it.weekData.size}"
            if(it.weekData.isNotEmpty()) {
                Glide.with(this).load(it.weekData[0].song.al.picUrl).into(binding.latestSongImgView)
            }
        })
        viewModel.playedList().registerLifeCycle(lifeCycleProvide)
            .doOnError { context?.toast(it.message?:"歌单请求错误") }
            .doOnComplete {
                songListAdapter.setList(it)
            }.post()
    }

}