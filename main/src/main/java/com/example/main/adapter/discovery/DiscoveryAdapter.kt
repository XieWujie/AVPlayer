package com.example.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.main.databinding.DiscoveryFirstItemBinding
import com.example.main.databinding.DiscoveryListItemBinding

class DiscoveryAdapter :RecyclerView.Adapter<DiscoveryAdapter.ViewHolder>(){

    private val mList = ArrayList<String>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when(viewType){
            TOP->{
                val binding = DiscoveryFirstItemBinding.inflate(inflater,parent,false)
                binding.discoveryPageView.adapter = DiscoveryTopAdapter()
                ViewHolder(binding.root)
            }
            SECOND->{
                val view = RecyclerView(parent.context)
                view.layoutManager = LinearLayoutManager(parent.context,RecyclerView.HORIZONTAL,false)
                view.adapter = SDListAdapter()
                ViewHolder(view)
            }
            else->{
                val binding = DiscoveryListItemBinding.inflate(inflater,parent,false)
                ViewHolder(binding.root)
            }
        }
    }

    override fun getItemCount(): Int {
        return mList.size+2
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemViewType(position: Int) = when(position){
        0-> TOP
        1-> SECOND
        else-> LIST
    }

    companion object{
        val TOP = 0
        val SECOND = 1
        val LIST = 2
    }

    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){

    }
}