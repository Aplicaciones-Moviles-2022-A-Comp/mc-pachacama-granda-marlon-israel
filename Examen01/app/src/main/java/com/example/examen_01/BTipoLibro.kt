package com.example.examen_01

import android.os.Parcel
import android.os.Parcelable

class BTipoLibro (

    val idTipoLibro: Int,
    val tipoLibro: String?

) : Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(idTipoLibro)
        parcel.writeString(tipoLibro)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BTipoLibro> {
        override fun createFromParcel(parcel: Parcel): BTipoLibro {
            return BTipoLibro(parcel)
        }

        override fun newArray(size: Int): Array<BTipoLibro?> {
            return arrayOfNulls(size)
        }
    }

}