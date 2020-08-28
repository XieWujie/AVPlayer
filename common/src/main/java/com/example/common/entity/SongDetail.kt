package com.example.common.entity


import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import androidx.room.*
import com.example.common.adapter.ARTypeConverter
import kotlinx.android.parcel.Parcelize


data class ChargeInfo(
    val chargeMessage: Any,
    val chargeType: Int,
    val chargeUrl: Any,
    val rate: Int
)


@SuppressLint("ParcelCreator")
@Parcelize
data class Al(
    @ColumnInfo(name = "al_id")
    val id: Int,
    @ColumnInfo(name = "al_name")
    val name: String,
    @ColumnInfo(name = "al_pic")
    val pic: Long,
    @ColumnInfo(name = "al_picUrl")
    val picUrl: String,
    @ColumnInfo(name = "al_pic_str")
    val pic_str: String
):Parcelable



@Entity
@TypeConverters(ARTypeConverter::class)
data class SongDetail(
    @Embedded
    val al: Al,
    val ar: List<Ar>,
    val cd: String,
    val cf: String,
    val copyright: Int,
    val cp: Int,
    val djId: Int,
    val dt: Int,
    val eq: String,
    val fee: Int,
    val ftype: Int,
    @Embedded
    val h: H,
    @PrimaryKey
    val id: Int,
    @Embedded
    val l: L,
    @Embedded
    val m: M,
    val mark: Long,
    val mst: Int,
    val mv: Int,
    val name: String,
    val no: Int,
    val originCoverType: Int,
    val pop: Int,
    @Embedded
    val privilege: Privilege,
    val pst: Int,
    val publishTime: Long,
    val rt: String,
    val rtype: Int,
    val s_id: Int,
    val st: Int,
    val t: Int,
    val v: Int

):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(Al::class.java.classLoader)!!,
        parcel.createTypedArrayList(Ar)!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readInt(),
        parcel.readParcelable(H::class.java.classLoader)!!,
        parcel.readInt(),
        parcel.readParcelable(L::class.java.classLoader)!!,
        parcel.readParcelable(M::class.java.classLoader)!!,
        parcel.readLong(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readParcelable(Privilege::class.java.classLoader)!!,
        parcel.readInt(),
        parcel.readLong(),
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(al, flags)
        parcel.writeTypedList(ar)
        parcel.writeString(cd)
        parcel.writeString(cf)
        parcel.writeInt(copyright)
        parcel.writeInt(cp)
        parcel.writeInt(djId)
        parcel.writeInt(dt)
        parcel.writeString(eq)
        parcel.writeInt(fee)
        parcel.writeInt(ftype)
        parcel.writeParcelable(h, flags)
        parcel.writeInt(id)
        parcel.writeParcelable(l, flags)
        parcel.writeParcelable(m, flags)
        parcel.writeLong(mark)
        parcel.writeInt(mst)
        parcel.writeInt(mv)
        parcel.writeString(name)
        parcel.writeInt(no)
        parcel.writeInt(originCoverType)
        parcel.writeInt(pop)
        parcel.writeParcelable(privilege, flags)
        parcel.writeInt(pst)
        parcel.writeLong(publishTime)
        parcel.writeString(rt)
        parcel.writeInt(rtype)
        parcel.writeInt(s_id)
        parcel.writeInt(st)
        parcel.writeInt(t)
        parcel.writeInt(v)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SongDetail> {
        override fun createFromParcel(parcel: Parcel): SongDetail {
            return SongDetail(parcel)
        }

        override fun newArray(size: Int): Array<SongDetail?> {
            return arrayOfNulls(size)
        }
    }
}


data class Ar(
   // val alias: List<Any>,
    val id: Int,
    val name: String
  //  val tns: List<Any>
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Ar> {
        override fun createFromParcel(parcel: Parcel): Ar {
            return Ar(parcel)
        }

        override fun newArray(size: Int): Array<Ar?> {
            return arrayOfNulls(size)
        }
    }
}

@SuppressLint("ParcelCreator")
@Parcelize
data class H(
    @ColumnInfo(name = "h_br")
    val br: Int,
    @ColumnInfo(name = "h_fid")
    val fid: Int,
    @ColumnInfo(name = "h_size")
    val size: Int
    // val vd: Int
):Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class L(
    @ColumnInfo(name = "l_br")
    val br: Int,
    @ColumnInfo(name = "l_fid")
    val fid: Int,
    @ColumnInfo(name = "l_size")
    val size: Int,
    @ColumnInfo(name = "l_vd")
    val vd: String
):Parcelable


@SuppressLint("ParcelCreator")
@Parcelize
data class M(
    @ColumnInfo(name = "m_br")
    val br: Int,
    @ColumnInfo(name = "m_fid")
    val fid: Int,
    @ColumnInfo(name = "m_size")
    val size: Int
    //   val vd: Int
):Parcelable
