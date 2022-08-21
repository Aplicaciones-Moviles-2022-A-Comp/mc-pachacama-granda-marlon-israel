package com.example.proyectosegundobimestre

import android.os.Parcel
import android.os.Parcelable

class FirestoreUser(
    var user_id: String?,
    var user_bank: String?,
    var user_ci: String?,
    var user_email: String?,
    var user_location: String?,
    var user_name: String?,
    var user_phone: String?,
    var user_username: String?,
    var account_balance: Double,
    var account_num: String?,
    var account_pass: String?,
    var account_last_access: String?
) :Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun toString(): String {
        return "${user_name}"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(user_id)
        parcel.writeString(user_bank)
        parcel.writeString(user_ci)
        parcel.writeString(user_email)
        parcel.writeString(user_location)
        parcel.writeString(user_name)
        parcel.writeString(user_phone)
        parcel.writeString(user_username)
        parcel.writeDouble(account_balance)
        parcel.writeString(account_num)
        parcel.writeString(account_pass)
        parcel.writeString(account_last_access)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FirestoreUser> {
        override fun createFromParcel(parcel: Parcel): FirestoreUser {
            return FirestoreUser(parcel)
        }

        override fun newArray(size: Int): Array<FirestoreUser?> {
            return arrayOfNulls(size)
        }
    }
}