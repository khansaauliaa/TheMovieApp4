package com.khansafzh.themovieapp.model

import android.os.Parcel
import android.os.Parcelable

class Model(
    var id: Int = 0,
    var title: String? = null,
    var synopsis: String? = null,
    var date: String? = null,
    var poster: String? = null,
    var thumb: String? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(synopsis)
        parcel.writeString(date)
        parcel.writeString(poster)
        parcel.writeString(thumb)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Model> {
        override fun createFromParcel(parcel: Parcel): Model {
            return Model(parcel)
        }

        override fun newArray(size: Int): Array<Model?> {
            return arrayOfNulls(size)
        }
    }
}