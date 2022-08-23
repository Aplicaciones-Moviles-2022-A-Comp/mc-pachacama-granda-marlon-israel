package com.example.examenrefactorado

import android.os.Parcel
import android.os.Parcelable

    class Biblioteca(
        var idBiblioteca: Int?,
        var nombreBiblioteca: String?,
        var yearFundacion: Int?,
        var city: String?,
        var direccion: String?,
        var telefono: Int?
    ) : Parcelable {

        override fun toString(): String {
            return "${nombreBiblioteca}"
        }

        constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt()
        ) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeInt(idBiblioteca!!)
            parcel.writeString(nombreBiblioteca)
            parcel.writeInt(yearFundacion!!)
            parcel.writeString(city)
            parcel.writeString(direccion)
            parcel.writeInt(telefono!!)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Biblioteca> {
            override fun createFromParcel(parcel: Parcel): Biblioteca {
                return Biblioteca(parcel)
            }

            override fun newArray(size: Int): Array<Biblioteca?> {
                return arrayOfNulls(size)
            }
        }


    }