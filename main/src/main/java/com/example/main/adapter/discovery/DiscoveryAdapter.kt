package com.example.main.adapter.discovery

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.*
import com.bumptech.glide.Glide
import com.example.conmon.base.AndroidLifeCycleProvide
import com.example.main.databinding.DiscoveryFirstItemBinding
import com.example.main.databinding.DiscoveryListItemBinding
import com.example.main.http.entity.Block
import com.example.route.AVRoute

class DiscoveryAdapter(val lifeCycleProvide: AndroidLifeCycleProvide) :RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private val mList = ArrayList<Block>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when(viewType){
            TOP ->{
                val binding = DiscoveryFirstItemBinding.inflate(inflater,parent,false)
                binding.discoveryPageView.adapter = DiscoveryTopAdapter()
                ViewHolder(binding.root)
            }
            SECOND ->{
                val view = RecyclerView(parent.context)
                view.layoutManager = LinearLayoutManager(parent.context,RecyclerView.HORIZONTAL,false)
                view.adapter = SDListAdapter()
                ViewHolder(view)
            }
            else->{
                val binding = DiscoveryListItemBinding.inflate(inflater,parent,false)
                CommendViewHolder(binding)
            }
        }
    }

    override fun getItemCount(): Int {
        return mList.size+2
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(getItemViewType(position)){
            LIST->{
                (holder as CommendViewHolder).bind(mList[position-2])
            }
        }
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

    fun setList(list:List<Block>){
        mList.clear()
        mList.addAll(list)
        notifyDataSetChanged()
    }

    private inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){

    }

    private inner class CommendViewHolder(val binding:DiscoveryListItemBinding):RecyclerView.ViewHolder(binding.root){

        private val SLIDE_PLAYLIST = "HOMEPAGE_SLIDE_PLAYLIST"
        private val SLIDE_SONGLIST_ALIGN = "HOMEPAGE_SLIDE_SONGLIST_ALIGN"
        private val SLIDE_MLOG = "HOMEPAGE_SLIDE_MLOG"
        private val HOMEPAGE_SLIDE_LISTEN_LIVE = "HOMEPAGE_SLIDE_LISTEN_LIVE"
        private val HOMEPAGE_NEW_SONG_NEW_ALBUM = "HOMEPAGE_NEW_SONG_NEW_ALBUM"
        private val HOMEPAGE_SLIDE_PLAYABLE_RESOURCE = "HOMEPAGE_SLIDE_PLAYABLE_RESOURCE"

        fun bind(block:Block){
            binding.itemTitleText.text = block.uiElement?.subTitle?.title?:""
            binding.actionText.text = block.uiElement?.button?.text?:""
            val recyclerView = binding.recommendListView
            LinearSnapHelper().attachToRecyclerView(recyclerView)
            when(block.showType){
                SLIDE_PLAYLIST->{
                    binding.actionView.visibility = View.GONE
                    recyclerView.layoutManager = LinearLayoutManager(binding.root.context,RecyclerView.HORIZONTAL,false)
                    recyclerView.adapter = SongListCommendAdapter().apply {
                        setList(block.creatives?: emptyList())
                    }
                    binding.actionText.setOnClickListener {
                        AVRoute().route("songlist/songsquare",binding.root.context,{}).execute()
                    }
                }
                SLIDE_SONGLIST_ALIGN->{
                    binding.actionView.visibility = View.VISIBLE
                    recyclerView.layoutManager = GridLayoutManager(binding.root.context,3,RecyclerView.HORIZONTAL,false)
                    val adapter = ListCanPlayAdapter(lifeCycleProvide)
                    recyclerView.adapter = adapter
                    val resources = block.creatives.flatMap { it.resources }
                    adapter.setList(resources)
                }
            }

        }

    }
}