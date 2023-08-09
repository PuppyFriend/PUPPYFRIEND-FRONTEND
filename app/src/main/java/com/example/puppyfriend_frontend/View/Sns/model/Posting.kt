package com.example.puppyfriend_frontend.View.Sns.model

import android.os.Parcel
import android.os.Parcelable

data class Posting(
    val date: String = "weer",
    val image: String,
    val content: String = "WEr",
    val contentBackgroundColor: Int = 1
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(date)
        parcel.writeString(image)
        parcel.writeString(content)
        parcel.writeInt(contentBackgroundColor)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Posting> {
        override fun createFromParcel(parcel: Parcel): Posting {
            return Posting(parcel)
        }

        override fun newArray(size: Int): Array<Posting?> {
            return arrayOfNulls(size)
        }
    }
}
