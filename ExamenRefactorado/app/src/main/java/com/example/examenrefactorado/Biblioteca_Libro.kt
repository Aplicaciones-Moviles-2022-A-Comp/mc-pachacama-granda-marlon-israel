package com.example.examenrefactorado

import android.os.Parcel
import android.os.Parcelable

class Biblioteca_Libro (

    val idBiblioteca_Libro: Int,
    val idBiblioteca: Int,
    val idLibro: Int

) : Parcelable {


    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(idBiblioteca_Libro)
        parcel.writeInt(idBiblioteca)
        parcel.writeInt(idLibro)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Biblioteca_Libro> {
        override fun createFromParcel(parcel: Parcel): Biblioteca_Libro {
            return Biblioteca_Libro(parcel)
        }

        override fun newArray(size: Int): Array<Biblioteca_Libro?> {
            return arrayOfNulls(size)
        }
    }

}