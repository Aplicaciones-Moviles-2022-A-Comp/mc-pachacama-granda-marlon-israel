package com.example.proyectosegundobimestre

import android.os.Parcel
import android.os.Parcelable

class FirestoreBank(
    var bank_id:String?,
    var bank_location: String?,
    var bank_location_min: String?,
    var bank_name: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun toString(): String {
        return "${bank_name}"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(bank_id)
        parcel.writeString(bank_location)
        parcel.writeString(bank_location_min)
        parcel.writeString(bank_name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FirestoreBank> {
        override fun createFromParcel(parcel: Parcel): FirestoreBank {
            return FirestoreBank(parcel)
        }

        override fun newArray(size: Int): Array<FirestoreBank?> {
            return arrayOfNulls(size)
        }
    }
}