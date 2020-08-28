package com.example.songlist.adapter

import androidx.annotation.LayoutRes
import com.example.songlist.R
import com.example.songlist.bean.Playlists
import java.util.ArrayList

class AllSongListAdapter(@LayoutRes layoutId: Int, list: ArrayList<Playlists>) :
    BaseRecyclerViewAdapter<Playlists>(layoutId, list) {

    override fun bindViewHolder(holder: ViewHolder, item: Playlists, position: Int) {
        holder.setText(R.id.text_all_song_list_item_name, list[position].name)
            .setText(R.id.text_all_song_list_item_play_count, transNum(list[position].playCount))
            .setImage(R.id.image_all_song_list_item, list[position].coverImgUrl)
    }


    private fun transNum(num: Long): String {
        return when {
            num < 100_000 -> num.toString()
            num < 100_000_000 -> "${(num / 100_000)}万"
            else -> "${num.toFloat() / 100_000_000.toFloat()}亿"
        }
    }


}