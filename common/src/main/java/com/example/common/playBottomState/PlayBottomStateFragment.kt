package com.example.common.playBottomState

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.common.R
import com.example.common.databinding.PlayBottomLayoutBinding
import com.example.common.base.AndroidLifeCycleProvide
import com.example.common.extension.bind
import com.example.common.playservice.IConnectionCallback
import com.example.common.playservice.PlayerService
import com.example.common.playservice.ResponseConnection
import java.util.*

class PlayBottomStateFragment :Fragment(),IConnectionCallback{

    private lateinit var binding:PlayBottomLayoutBinding
    private var lyric:SortedMap<Int,String>? = null
    private val handle = Handler()
    private var songId = 0
    private lateinit var viewModel: ViewModel
    private lateinit var lifeCycleProvide: AndroidLifeCycleProvide
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifeCycleProvide = AndroidLifeCycleProvide(this)
        viewModel = ViewModelProvider(this)[ViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = inflater.bind(R.layout.play_bottom_layout,container)
        dispatchEvent()
        return binding.root
    }


    fun dispatchEvent(){
        ResponseConnection.registerObservable(this, lifeCycleProvide)
        val barView = binding.circlePlayBar
        barView.setOnClickListener {
            if(barView.getPlayState()){
                PlayerService.playConnection?.pause()
            }else{
                PlayerService.playConnection?.start()
            }
        }
        viewModel.songDetailLiveData.observe(viewLifecycleOwner,androidx.lifecycle.Observer {
//            Glide.with(this).load(it.al.picUrl).into(binding.songCoverView)
//            binding.songNameText.text = it.name
        })
    }

    override fun started(id: Int) {
        if(songId != id){
            songId = id
            viewModel.getSongDetail(id)
        }
        binding.circlePlayBar.setIsPlaying(true)

    }

    override fun pause(id: Int) {
        binding.circlePlayBar.setIsPlaying(false)
    }

    override fun playTime(time: Int) {
        binding.circlePlayBar.setAllTime(time)
    }

    override fun playedTime(time: Int) {
        binding.circlePlayBar.setTime(time)
    }

    override fun lyric(lyric: SortedMap<Int, String>) {
        handle.post {
            this.lyric = lyric
        }
    }

    override fun lyricChange(time: Int) {
        ResponseConnection.registerObservable(this, lifeCycleProvide)
        handle.post {
            val lyric = lyric?:return@post
            binding.songLrcView.text = lyric[time]
        }
    }
}