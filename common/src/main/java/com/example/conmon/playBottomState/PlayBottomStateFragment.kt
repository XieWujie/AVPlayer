package com.example.conmon.playBottomState

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.common.R
import com.example.common.databinding.PlayBottomLayoutBinding
import com.example.conmon.base.AndroidLifeCycleProvide
import com.example.conmon.extension.bind
import com.example.conmon.playservice.IConnectionCallback
import com.example.conmon.playservice.PlayerService
import com.example.conmon.playservice.ResponseConnection
import java.util.*

class PlayBottomStateFragment :Fragment(),IConnectionCallback{

    private lateinit var binding:PlayBottomLayoutBinding
    private var lyric:SortedMap<Int,String>? = null
    private val handle = Handler()
    private lateinit var lifeCycleProvide: AndroidLifeCycleProvide
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifeCycleProvide = AndroidLifeCycleProvide(this)
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
    }

    override fun started(id: Int) {
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