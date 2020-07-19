package com.example.main.http.entry

data class LikeList(
    val checkPoint: Long,
    val code: Int,
    val ids: List<Int>
)