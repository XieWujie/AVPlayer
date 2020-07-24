package com.example.main.adapter.mine

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.conmon.extension.bind
import com.example.main.R
import com.example.main.databinding.PlayListItemBinding
import com.example.main.http.entry.Playlist
import kotlin.collections.ArrayList

class SongListAdapter :RecyclerView.Adapter<SongListAdapter.ViewHolder>(){

    private val mList = ArrayList<Playlist>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = LayoutInflater.from(parent.context).bind<PlayListItemBinding>(R.layout.play_list_item,parent)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    fun setList(list:List<Playlist>){
        mList.clear()
        mList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mList[position])
    }


    inner class ViewHolder(val binding:PlayListItemBinding):RecyclerView.ViewHolder(binding.root){

        @SuppressLint("SetTextI18n")
        fun bind(playList: Playlist){
            binding.listCountText.text = "${playList.trackCount}首"
            binding.listNameText.text = playList.name
            Glide.with(binding.listCoverView).load(playList.coverImgUrl).into(binding.listCoverView)
            binding.root.setOnClickListener {

            }
        }
    }
}