package com.example.songlist.bean

import com.google.gson.annotations.SerializedName


data class HeightQualitySongListBean(
    @SerializedName("code")
    var code: Int,
    @SerializedName("lasttime")
    var lasttime: Long,
    @SerializedName("more")
    var more: Boolean,
    @SerializedName("playlists")
    var playlists: List<Playlists>,
    @SerializedName("total")
    var total: Int
) : IBean

data class Playlists(
    @SerializedName("commentCount")//评论数
    var commentCount: Int,
    @SerializedName("copywriter")//文案
    var copywriter: String,
    @SerializedName("coverImgUrl")//图片链接
    var coverImgUrl: String,
    @SerializedName("description")//歌单描述
    var description: String,
    @SerializedName("updateTime")//更新时间 精品歌单分页用
    var updateTime: Long,
    @SerializedName("id")//歌单Id
    var id: Long,
    @SerializedName("name")//歌单名
    var name: String,
    @SerializedName("ordered")//是否已进行排序
    var ordered: Boolean,
    @SerializedName("playCount")//播放量
    var playCount: Long,
    @SerializedName("shareCount")//分享量
    var shareCount: Int,
    @SerializedName("subscribedCount")//订阅量
    var subscribedCount: Int,
    @SerializedName("tag")//主要标签
    var tag: String,
    @SerializedName("tags")//所有标签
    var tags: List<String>,
    @SerializedName("trackCount")//包含歌曲数量
    var trackCount: Int,
    @SerializedName("userId")//歌单创建者Id
    var userId: Long
)