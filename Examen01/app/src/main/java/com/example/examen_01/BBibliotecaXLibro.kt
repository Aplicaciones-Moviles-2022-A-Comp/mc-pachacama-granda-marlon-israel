package com.example.examen_01

import android.os.Parcel
import android.os.Parcelable

class BBibliotecaXLibro (

    val idBibliotecaXLibro: Int,
    var nombreBilbiotecaXLibro: String?,
    val idBiblioteca: Int,
    val idLibro: Int

) : Parcelable {

    override fun toString(): String {
        return "${nombreBilbiotecaXLibro}"
    }

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(idBibliotecaXLibro)
        parcel.writeString(nombreBilbiotecaXLibro)
        parcel.writeInt(idBiblioteca)
        parcel.writeInt(idLibro)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BBibliotecaXLibro> {
        override fun createFromParcel(parcel: Parcel): BBibliotecaXLibro {
            return BBibliotecaXLibro(parcel)
        }

        override fun newArray(size: Int): Array<BBibliotecaXLibro?> {
            return arrayOfNulls(size)
        }
    }

}