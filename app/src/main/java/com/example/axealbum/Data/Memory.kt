package com.example.axealbum.Data

import android.os.Parcel
import android.os.Parcelable

class Memory(var title: String, var image: Int, var comment: String) : Parcelable {

    constructor(parcel: Parcel) : this(parcel.readString(), parcel.readInt(), parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeInt(image)
        parcel.writeString(comment)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Memory> {
        override fun createFromParcel(parcel: Parcel): Memory {
            return Memory(parcel)
        }

        override fun newArray(size: Int): Array<Memory?> {
            return arrayOfNulls(size)
        }
    }
}