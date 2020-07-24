package com.example.playerservice

import android.os.Parcel
import android.os.Parcelable

data class Lyric(val time:Int,val lyric:String) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()?:"")

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(time)
        parcel.writeString(lyric)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Lyric> {
        override fun createFromParcel(parcel: Parcel): Lyric {
            return Lyric(parcel)
        }

        override fun newArray(size: Int): Array<Lyric?> {
            return arrayOfNulls(size)
        }

    }
}