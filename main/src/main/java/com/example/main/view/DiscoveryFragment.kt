package com.example.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.conmon.base.AVFragment
import com.example.conmon.extension.bind
import com.example.main.R
import com.example.main.adapter.DiscoveryTopAdapter
import com.example.main.databinding.FragmentDiscoveryBinding
import com.example.main.viewmodel.DiscoveryViewModel
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class DiscoveryFragment :AVFragment<DiscoveryViewModel>(),KodeinAware{

    override val kodein = Kodein.lazy {
        extend(parent)
    }
    override val viewModel: DiscoveryViewModel by instance()
    lateinit var binding:FragmentDiscoveryBinding
    private val topAdapter by instance<DiscoveryTopAdapter>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = inflater.bind(R.layout.fragment_discovery,container)
        init()
        return binding.root
    }

    private fun init(){
        binding.topPage.adapter = topAdapter
    }
}