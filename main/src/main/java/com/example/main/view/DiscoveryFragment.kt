package com.example.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.dibus.AndroidLifeCycleProvide
import com.example.common.base.AVFragment
import com.example.common.extension.bind
import com.example.common.extension.toast
import com.example.main.R
import com.example.main.adapter.discovery.DiscoveryAdapter
import com.example.main.databinding.FragmentDiscoveryBinding
import com.example.main.viewmodel.DiscoveryViewModel
import com.dibus.AutoWire
import com.dibus.DiBus
import dibus.main.DiscoveryFragmentCreator

class DiscoveryFragment :AVFragment(){


    @AutoWire
    lateinit var viewModel: DiscoveryViewModel
    private lateinit var binding:FragmentDiscoveryBinding
    private val adapter = DiscoveryAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        DiscoveryFragmentCreator.inject(this)
        binding = inflater.bind(R.layout.fragment_discovery,container)
        init()
        dispatchEvent()
        return binding.root
    }

    private fun init(){
        binding.discoveryRecycleView.layoutManager = LinearLayoutManager(context)
        binding.discoveryRecycleView.adapter = adapter
    }

    private fun dispatchEvent(){
        viewModel.blocks()
            .doOnError { context?.toast(it.message?:"请求错误") }
            .doOnComplete {
                adapter.setList(it)
            }
            .post()
    }
}