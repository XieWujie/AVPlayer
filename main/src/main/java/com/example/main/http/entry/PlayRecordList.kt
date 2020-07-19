package com.example.main.http.entry

data class PlayRecordList(
    val code: Int,
    val weekData: List<WeekData>
)

data class WeekData(
    val playCount: Int,
    val score: Int,
    val song: Song
)

data class Song(
    val a: Any,
    val al: Al,
    val alia: List<Any>,
    val ar: List<Ar>,
    val cd: String,
    val cf: String,
    val copyright: Int,
    val cp: Int,
    val crbt: Any,
    val djId: Int,
    val dt: Int,
    val eq: String,
    val fee: Int,
    val ftype: Int,
    val h: H,
    val id: Int,
    val l: L,
    val m: M,
    val mark: Long,
    val mst: Int,
    val mv: Int,
    val name: String,
    val no: Int,
    val noCopyrightRcmd: Any,
    val originCoverType: Int,
    val pop: Int,
    val privilege: Privilege,
    val pst: Int,
    val publishTime: Long,
    val rt: String,
    val rtUrl: Any,
    val rtUrls: List<Any>,
    val rtype: Int,
    val rurl: Any,
    val s_id: Int,
    val st: Int,
    val t: Int,
    val v: Int
)

data class Al(
    val id: Int,
    val name: String,
    val pic: Long,
    val picUrl: String,
    val pic_str: String,
    val tns: List<Any>
)

data class Ar(
    val alias: List<Any>,
    val id: Int,
    val name: String,
    val tns: List<Any>
)

data class H(
    val br: Int,
    val fid: Int,
    val size: Int,
    val vd: Int
)

data class L(
    val br: Int,
    val fid: Int,
    val size: Int,
    val vd: Int
)

data class M(
    val br: Int,
    val fid: Int,
    val size: Int,
    val vd: Int
)

data class Privilege(
    val cp: Int,
    val cs: Boolean,
    val dl: Int,
    val fee: Int,
    val fl: Int,
    val flag: Int,
    val id: Int,
    val maxbr: Int,
    val payed: Int,
    val pl: Int,
    val preSell: Boolean,
    val sp: Int,
    val st: Int,
    val subp: Int,
    val toast: Boolean
)