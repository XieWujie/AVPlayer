package com.example.main.adapter.discovery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.conmon.extension.bind
import com.example.main.R
import com.example.main.databinding.SencondListItemBinding

class SDListAdapter :RecyclerView.Adapter<SDListAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val binding = LayoutInflater.from(parent.context).bind<SencondListItemBinding>(R.layout.sencond_list_item,parent)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return itemViewID.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bind(position)
    }

    class ViewHolder(val binding:SencondListItemBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(position:Int){
            binding.itemView.setImageResource(itemViewID[position])
            binding.itemName.text = itemName[position]
        }
    }

    companion object{
        private val itemViewID = intArrayOf(R.drawable.ic_recommend,
            R.drawable.ic_song_list,R.drawable.ic_rank,R.drawable.ic_station,
            R.drawable.ic_live,R.drawable.ic_fire,R.drawable.ic_album,R.drawable.ic_sing_chat)
        private val itemName = arrayOf("每日推荐","歌单","排行榜","电台","直播","火前留名","数字专辑","唱聊")
    }
}