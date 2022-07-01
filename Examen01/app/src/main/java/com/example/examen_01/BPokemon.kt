package com.example.examen_01

import android.os.Parcel
import android.os.Parcelable

class BPokemon (

    val idPokemon: Int,
    var nombre:String?,
    var tipo: String?

) : Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(idPokemon)
        parcel.writeString(nombre)
        parcel.writeString(tipo)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BPokemon> {
        override fun createFromParcel(parcel: Parcel): BPokemon {
            return BPokemon(parcel)
        }

        override fun newArray(size: Int): Array<BPokemon?> {
            return arrayOfNulls(size)
        }
    }


}