package com.example.common.playBottomState

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.common.R
import com.example.common.databinding.PlayBottomLayoutBinding
import com.example.common.extension.bind
import com.example.common.playservice.PlayEvent
import com.example.common.playservice.PlayerService
import com.dibus.BusEvent
import com.dibus.DiBus
import com.dibus.THREAD_POLICY_MAIN
import dibus.common.PlayBottomStateFragmentCreator
import java.util.*

class PlayBottomStateFragment :Fragment(){

    private lateinit var binding:PlayBottomLayoutBinding
    private var lyric:SortedMap<Int,String>? = null
    private var songId = 0
    private lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        PlayBottomStateFragmentCreator.inject(this)
        viewModel = ViewModelProvider(this)[ViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = inflater.bind(R.layout.play_bottom_layout,container)
        dispatchEvent()
        return binding.root
    }


    private fun dispatchEvent(){

        val barView = binding.circlePlayBar
        barView.setOnClickListener {
            if(barView.getPlayState()){
                PlayerService.playConnection?.pause()
            }else{
                PlayerService.playConnection?.start()
            }
        }
        viewModel.songDetailLiveData.observe(viewLifecycleOwner,Observer {
          //  Glide.with(this).load(it.al.picUrl).into(binding.songCoverView)
        //    binding.songNameText.text = it.name
        })
    }

    @BusEvent(stick = true)
     fun started(event:PlayEvent.Started) {
        if(songId !=event.id){
            songId = event.id
            viewModel.getSongDetail(event.id)
        }
        binding.circlePlayBar.setIsPlaying(true)

    }

    @BusEvent
     fun pause(pause: PlayEvent.Pause) {
        binding.circlePlayBar.setIsPlaying(false)
    }

    @BusEvent(stick = true)
     fun playTime(playTime: PlayEvent.PlayTime) {
        binding.circlePlayBar.setAllTime(playTime.time)
    }

    @BusEvent
     fun playedTime(playedTime: PlayEvent.PlayedTime) {
        binding.circlePlayBar.setTime(playedTime.time)
    }

    @BusEvent(threadPolicy = THREAD_POLICY_MAIN,stick = true)
     fun lyric(lyric: PlayEvent.Lyric) {
        this.lyric = lyric.lyric
    }

    @BusEvent(threadPolicy = THREAD_POLICY_MAIN)
     fun lyricChange(lyricChange: PlayEvent.LyricChange) {
        val lyric = lyric?:return
        binding.songLrcView.text = lyric[lyricChange.time]
    }
}