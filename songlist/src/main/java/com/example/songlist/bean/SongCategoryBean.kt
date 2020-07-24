package com.example.songlist.bean

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class SongCategoryBean(
    val all: All,
    val categories: Categories,
    val code: Int,
    val sub: List<Sub>
) : IBean

data class All(
    val activity: Boolean,
    val category: Int,
    val hot: Boolean,
    val imgId: Int,
    val imgUrl: Any,
    val name: String,
    val resourceCount: Int,
    val resourceType: Int,
    val type: Int
)

data class Categories(
    @SerializedName("0")
    val cat0: String,
    @SerializedName("1")
    val cat1: String,
    @SerializedName("2")
    val cat2: String,
    @SerializedName("3")
    val cat3: String,
    @SerializedName("4")
    val cat4: String
)

data class Sub(
    val category: Int,
    val hot: Boolean,
    val name: String,
    val isTab: Boolean

)