package com.example.examen_01

import android.os.Parcel
import android.os.Parcelable

class BBiblioteca (
    var id: Int?,
    var nombreBiblioteca: String?,
	var yearFundacion: String?,
    var ciudad: String?,
	var direccion: String?,
	var telefono: String?

) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
    ) {
    }
    override fun toString(): String {
        return  "Name of Biliotec =${nombreBiblioteca}\n" +
                "Year of Fundation: = ${yearFundacion}\n" +
                "City: ${ciudad} \n" +
                "Addres: ${direccion} \n" +
                "Telephone: ${telefono} \n"

    }
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        if(id !=null){
            parcel.writeInt(id!!)
        }else{
            parcel.writeInt(0)
        }
        parcel.writeString(nombreBiblioteca)
        parcel.writeValue(yearFundacion)
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