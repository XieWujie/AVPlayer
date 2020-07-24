package com.example.main.adapter.discovery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.conmon.extension.runOnMainThread
import com.example.conmon.playservice.PlayerService
import com.example.conmon.playservice.ResponseConnection
import com.example.main.R
import com.example.main.databinding.DiscoveryCanPlayItemBinding
import com.example.main.http.entity.Creative
import com.example.main.http.entity.Resource

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

    class ViewHolder(val binding:DiscoveryCanPlayItemBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(resource: Resource){
            binding.songTitle.text = resource.uiElement?.mainTitle?.title?:""
            binding.songSubTitle.text = resource.uiElement?.subTitle?.title?:""
            Glide.with(binding.songCoverView).load(resource.uiElement.image.imageUrl).into(binding.songCoverView)
            binding.songPlayStateView.setOnClickListener {view->
                if(!view.isSelected) {
                    val id = resource.resourceId.toInt()
                    PlayerService.playConnection?.rquestPlaySong(id, null)
                    ResponseConnection.registerStartedEvent {
                        if(id== it){
                            runOnMainThread {
                                view.isSelected = true
                                view.background = null
                            }
                        }
                    }
                    ResponseConnection.registerPauseEvent {
                        if(id == it){
                            runOnMainThread {
                                view.isSelected = false
                                view.setBackgroundResource(R.drawable.radius_rectangle)
                            }
                        }
                    }
                }
            }
        }
    }
}