package com.example.conmon.playBottomState

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.common.R
import com.example.common.databinding.PlayBottomLayoutBinding
import com.example.conmon.extension.bind
import com.example.conmon.playservice.ResponseConnection
import java.util.*

class PlayBottomStateFragment :Fragment(){

    private lateinit var binding:PlayBottomLayoutBinding
    private var lyric:TreeMap<Int,String>? =null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = inflater.bind(R.layout.play_bottom_layout,container)
        dispatchEvent()
        return binding.root
    }

    fun dispatchEvent(){
        val circlePlayBar = binding.circlePlayBar
        circlePlayBar.setAllTime(1000)
        ResponseConnection.registerLyricChangeEvent {
            circlePlayBar.setTime(it.toFloat())
            binding.songLrcView.text = lyric?.get(it)?:"lyric"
        }
        ResponseConnection.registerLyricEvent {
            lyric = it
        }

    }
}