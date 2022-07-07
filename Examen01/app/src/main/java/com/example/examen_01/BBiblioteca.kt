package com.example.examen_01

import android.os.Parcel
import android.os.Parcelable

class BBiblioteca (

    val idBiblioteca: Int,
    var nombreBiblioteca: String?,
	var yearFundacion: String?,
    var ciudad: String?,
	var direccion: String?,
	var telefono: String?

) : Parcelable {

    override fun toString(): String {
        return "${nombreBiblioteca}"
    }

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
        parcel.writeInt(idBiblioteca)
        parcel.writeString(nombreBiblioteca)
        parcel.writeString(yearFundacion)
        parcel.writeString(ciudad)
        parcel.writeString(direccion)
        parcel.writeString(telefono)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BBiblioteca> {
        override fun createFromParcel(parcel: Parcel): BBiblioteca {
            return BBiblioteca(parcel)
        }

        override fun newArray(size: Int): Array<BBiblioteca?> {
            return arrayOfNulls(size)
        }
    }


}