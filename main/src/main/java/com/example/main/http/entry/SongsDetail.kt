package com.example.main.http.entry

data class SongsDetailEntry(
    val code: Int,
    val privileges: List<Privilege>,
    val songs: List<Song>
)
data class ChargeInfo(
    val chargeMessage: Any,
    val chargeType: Int,
    val chargeUrl: Any,
    val rate: Int
)