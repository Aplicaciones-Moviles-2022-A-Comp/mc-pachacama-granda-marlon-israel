package com.example.proyectosegundobimestre

import android.os.Parcel
import android.os.Parcelable

class FirestoreAccount(
    var account_id: String?,
    var account_num: String?,
    var user_bank_id: String?,
    var user_bank_location_min: String?,
    var user_bakn_name: String?,
    var user_email: String?,
    var user_id: String?,
    var user_name: String?,
    var user_username: String?,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun toString(): String {
        return "${user_name}"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(account_id)
        parcel.writeString(account_num)
        parcel.writeString(user_bank_id)
        parcel.writeString(user_bank_location_min)
        parcel.writeString(user_bakn_name)
        parcel.writeString(user_email)
        parcel.writeString(user_id)
        parcel.writeString(user_name)
        parcel.writeString(user_username)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FirestoreAccount> {
        override fun createFromParcel(parcel: Parcel): FirestoreAccount {
            return FirestoreAccount(parcel)
        }

        override fun newArray(size: Int): Array<FirestoreAccount?> {
            return arrayOfNulls(size)
        }
    }
}