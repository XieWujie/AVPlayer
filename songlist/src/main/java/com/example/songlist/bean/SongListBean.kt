package com.example.songlist.bean

import com.google.gson.annotations.SerializedName


data class SongListBean(
    @SerializedName("cat")
    var cat: String,
    @SerializedName("code")
    var code: Int,
    @SerializedName("more")
    var more: Boolean,
    @SerializedName("playlists")
    var playlists: List<Playlists>,
    @SerializedName("total")
    var total: Int
)

