package com.example.thestore

import android.os.Parcel
import android.os.Parcelable

data class Prudects (var id:String?,var prudectName:String?,var price:Int,var photoID:Int,var description:String?,var ammount:Int):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(prudectName)
        parcel.writeInt(price)
        parcel.writeInt(photoID)
        parcel.writeString(description)
        parcel.writeInt(ammount)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Prudects> {
        override fun createFromParcel(parcel: Parcel): Prudects {
            return Prudects(parcel)
        }

        override fun newArray(size: Int): Array<Prudects?> {
            return arrayOfNulls(size)
        }
    }
}