package com.example.main.adapter.discovery
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.main.databinding.SongListSubItemBinding
import com.example.main.http.entity.Creative

class SongListCommendAdapter :RecyclerView.Adapter<SongListCommendAdapter.ViewHolder>(){

    private val mList = ArrayList<Creative>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SongListSubItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return mList.size
    }
    fun setList(list:List<Creative>){
        mList.clear()
        mList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mList[position])
    }

    class ViewHolder(val binding:SongListSubItemBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(creative: Creative){
            binding.listDiscriptionText.text = creative.uiElement?.mainTitle?.title?:""
            Glide.with(binding.listCoverView).load(creative.uiElement.image.imageUrl).into(binding.listCoverView)
            val resource = creative.resources
            if(resource.isNotEmpty()){
                binding.playCountText.text =  "${resource[0].resourceExtInfo?.playCount}"
            }
        }
    }
}