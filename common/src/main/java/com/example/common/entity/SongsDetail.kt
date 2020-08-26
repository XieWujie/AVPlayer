package com.example.common.entity

import android.annotation.SuppressLint
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class SongsDetail(
    val code: Int,
    val privileges: List<Privilege>,
    val songs: List<SongDetail>
): Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
@Entity
data class Privilege(
    @ColumnInfo(name = "p_cp")
    @PrimaryKey
    val cp: Int,
    @ColumnInfo(name = "p_cs")
    val cs: Boolean,
    @ColumnInfo(name = "p_dl")
    val dl: Int,
    @ColumnInfo(name = "p_fee")
    val fee: Int,
    @ColumnInfo(name = "p_fl")
    val fl: Int,
    @ColumnInfo(name = "p_flag")
    val flag: Int,
    @ColumnInfo(name = "p_id")
    val id: Int,
    @ColumnInfo(name = "p_max_br")
    val maxbr: Int,
    @ColumnInfo(name = "p_payed")
    val payed: Int,
    @ColumnInfo(name = "p_pl")
    val pl: Int,
    @ColumnInfo(name = "p_preSell")
    val preSell: Boolean,
    @ColumnInfo(name = "p_sp")
    val sp: Int,
    @ColumnInfo(name = "p_st")
    val st: Int,
    @ColumnInfo(name = "p_subp")
    val subp: Int,
    @ColumnInfo(name = "p_toast")
    val toast: Boolean
):Parcelable