package com.example.playerservice.http

data class LyricEntity(
    val code: Int,
    val klyric: Klyric,
    val lrc: Lrc,
    val qfy: Boolean,
    val sfy: Boolean,
    val sgc: Boolean,
    val tlyric: Tlyric
)

data class Klyric(
    val lyric: String,
    val version: Int
)

data class Lrc(
    val lyric: String,
    val version: Int
)

data class Tlyric(
    val lyric: Any,
    val version: Int
)