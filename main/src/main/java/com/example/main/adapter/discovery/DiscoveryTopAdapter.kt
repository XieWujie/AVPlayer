package com.example.main.adapter.discovery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.conmon.extension.bind
import com.example.main.R
import com.example.main.databinding.DiscoveryTopItemBinding

class DiscoveryTopAdapter :RecyclerView.Adapter<DiscoveryTopAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val binding = LayoutInflater.from(parent.context).bind<DiscoveryTopItemBinding>(R.layout.discovery_top_item,parent)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return 8
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }


    class ViewHolder(binding:DiscoveryTopItemBinding) :RecyclerView.ViewHolder(binding.root){


    }
}