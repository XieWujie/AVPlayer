package com.example.conmon.playBottomState

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.common.R
import com.example.common.databinding.PlayBottomLayoutBinding
import com.example.conmon.extension.bind

class PlayBottomStateFragment :Fragment(){

    private lateinit var binding:PlayBottomLayoutBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = inflater.bind(R.layout.play_bottom_layout,container)
        return binding.root
    }
}