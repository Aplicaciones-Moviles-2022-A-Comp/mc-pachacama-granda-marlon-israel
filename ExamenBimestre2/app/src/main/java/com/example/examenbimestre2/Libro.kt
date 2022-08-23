package com.example.examenbimestre2

import android.os.Parcel
import android.os.Parcelable

class Libro(
    var idLibro: String?,
    var idBiblioteca: String?,
    var nombreLibro:String?,
    var nombreAutor:String?,
    var yearEdicion: Int?,
    var categoria: String?,
    var precio: Double?,


    ) : Parcelable{
        constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
            parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readDouble()

        ) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(idLibro)
            parcel.writeString(idBiblioteca)
            parcel.writeString(nombreLibro)
            parcel.writeString(nombreAutor)
            parcel.writeInt(yearEdicion!!)
            parcel.writeString(categoria!!)
            parcel.writeDouble(precio!!)
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