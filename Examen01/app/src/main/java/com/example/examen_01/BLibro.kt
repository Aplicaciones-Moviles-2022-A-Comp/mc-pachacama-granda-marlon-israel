package com.example.examen_01

import android.os.Parcel
import android.os.Parcelable

class BLibro (

    val idLibro: Int,
    var nombreLibro:String?,
    var nombreBiblioteca: String?,
	var autor: String?,
	var yearEdicion: String?,
	var categoria: String?

) : Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(idLibro)
        parcel.writeString(nombreLibro)
        parcel.writeString(nombreBiblioteca)
        parcel.writeString(autor)
        parcel.writeString(yearEdicion)
        parcel.writeString(categoria)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BLibro> {
        override fun createFromParcel(parcel: Parcel): BLibro {
            return BLibro(parcel)
        }

        override fun newArray(size: Int): Array<BLibro?> {
            return arrayOfNulls(size)
        }
    }


}