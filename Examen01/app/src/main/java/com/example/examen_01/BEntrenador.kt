package com.example.examen_01

import android.os.Parcel
import android.os.Parcelable

class BEntrenador (

    val idEntrenador: Int,
    var nombre: String?,
    var edad: String?

) : Parcelable {

    override fun toString(): String {
        return "${nombre}"
    }

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(idEntrenador)
        parcel.writeString(nombre)
        parcel.writeString(edad)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BEntrenador> {
        override fun createFromParcel(parcel: Parcel): BEntrenador {
            return BEntrenador(parcel)
        }

        override fun newArray(size: Int): Array<BEntrenador?> {
            return arrayOfNulls(size)
        }
    }


}