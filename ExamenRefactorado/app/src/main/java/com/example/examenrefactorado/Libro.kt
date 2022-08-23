package com.example.examenrefactorado

import android.os.Parcel
import android.os.Parcelable

class Libro(
    var idLibro: Int?,
    var idBiblioteca: String?,
    var nombreLibro:String?,
    var nombreAutor:String?,
    var yearEdicion: Int?,
    var categoria: String?,
    var precio: Int?,


    ) : Parcelable{
        constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
            parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt()

        ) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeInt(idLibro!!)
            parcel.writeString(idBiblioteca)
            parcel.writeString(nombreLibro)
            parcel.writeString(nombreAutor)
            parcel.writeInt(yearEdicion!!)
            parcel.writeString(categoria!!)
            parcel.writeInt(precio!!)
        }

        override fun describeContents(): Int {
            return 0
        }

        override fun toString(): String {
            return "${nombreLibro}"
        }

        companion object CREATOR : Parcelable.Creator<Libro> {
            override fun createFromParcel(parcel: Parcel): Libro {
                return Libro(parcel)
            }

            override fun newArray(size: Int): Array<Libro?> {
                return arrayOfNulls(size)
            }
        }


    }