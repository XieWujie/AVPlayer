package com.example.main.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.conmon.base.AVFragment
import com.example.conmon.extension.bind
import com.example.conmon.extension.toast
import com.example.main.R
import com.example.main.adapter.discovery.DiscoveryAdapter
import com.example.main.databinding.FragmentDiscoveryBinding
import com.example.main.di.DISCOVERY_FRAGMENT_MODULE
import com.example.main.viewmodel.DiscoveryViewModel
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class DiscoveryFragment :AVFragment<DiscoveryViewModel>(),KodeinAware{

    override val kodein = Kodein.lazy {
        extend(parent)
        import(DISCOVERY_FRAGMENT_MODULE)
    }
    override val viewModel: DiscoveryViewModel by instance()
    lateinit var binding:FragmentDiscoveryBinding
    private val adapter = DiscoveryAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
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
        viewModel.blocks().registerLifeCycle(lifeCycleProvide)
            .doOnError { context?.toast(it.message?:"请求错误") }
            .doOnComplete {
                adapter.setList(it)
            }
            .post()
    }
}