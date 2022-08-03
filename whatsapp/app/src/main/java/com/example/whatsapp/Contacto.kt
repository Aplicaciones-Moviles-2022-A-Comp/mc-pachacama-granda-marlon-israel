package com.example.whatsapp

import android.os.Parcel
import android.os.Parcelable

class Contacto (
    var imagen:String?,
    var nombre: String?,
    var mensaje: String?,
    var fecha: String?
): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(imagen)
        parcel.writeString(nombre)
        parcel.writeString(mensaje)
        parcel.writeString(fecha)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Contacto> {
        override fun createFromParcel(parcel: Parcel): Contacto {
            return Contacto(parcel)
        }

        override fun newArray(size: Int): Array<Contacto?> {
            return arrayOfNulls(size)
        }
    }
}