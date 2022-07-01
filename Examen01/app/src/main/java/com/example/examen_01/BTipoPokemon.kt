package com.example.examen_01

import android.os.Parcel
import android.os.Parcelable

class BTipoPokemon (

    val idTipoPokemon: Int,
    val tipoPokemon: String?

) : Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(idTipoPokemon)
        parcel.writeString(tipoPokemon)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BTipoPokemon> {
        override fun createFromParcel(parcel: Parcel): BTipoPokemon {
            return BTipoPokemon(parcel)
        }

        override fun newArray(size: Int): Array<BTipoPokemon?> {
            return arrayOfNulls(size)
        }
    }

}