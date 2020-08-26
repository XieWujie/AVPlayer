package com.example.main.adapter.discovery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.common.playservice.IConnectionCallback
import com.example.common.playservice.PlayEvent
import com.example.common.playservice.PlayerService
import com.example.common.playservice.ResponseConnection
import com.example.main.R
import com.example.main.databinding.DiscoveryCanPlayItemBinding
import com.example.main.http.entity.Resource
import com.xie.di.BusEvent
import com.xie.di.DiBus
import com.xie.di.Service

class ListCanPlayAdapter :RecyclerView.Adapter<ListCanPlayAdapter.ViewHolder>(){

    private val mList = ArrayList<Resource>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val binding = DiscoveryCanPlayItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = mList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bind(mList[position])
    }

    fun setList(list: List<Resource>){
        mList.clear()
        mList.addAll(list)
    }

     class ViewHolder @Service constructor(val binding:DiscoveryCanPlayItemBinding):RecyclerView.ViewHolder(binding.root),IConnectionCallback{
        private  var id = 0
        init {
            DiBus.register(this)
        }
        fun bind(resource: Resource){
            binding.songTitle.text = resource.uiElement?.mainTitle?.title?:""
            binding.songSubTitle.text = resource.uiElement?.subTitle?.title?:""
            Glide.with(binding.songCoverView).load(resource.uiElement.image.imageUrl).into(binding.songCoverView)
            binding.songPlayStateView.setOnClickListener {view->
                if(!view.isSelected) {
                    id = resource.resourceId.toInt()
                    PlayerService.playConnection?.rquestPlaySong(id,null)
                }
            }
        }

        @BusEvent
        fun started(started: PlayEvent.Started) {
            if(started.id == this.id){
                binding.songPlayStateView.isSelected = true
                binding.songPlayStateView.background = null
            }else{
                binding.songPlayStateView.isSelected = false
                binding.songPlayStateView.setBackgroundResource(R.drawable.radius_rectangle)
            }
        }

    }

    companion object{
        private const val BIND_CONNECTION_TAG = 1
    }
}