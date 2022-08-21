package com.example.proyectosegundobimestre

import android.os.Parcel
import android.os.Parcelable

class FirestoreTransaction(
    var num_account_source: String?,
    var num_account_destiny: String?,
    var transaction_concept: String?,
    var transaction_amount: Double,
    var user_source_name: String?,
    var user_source_bank_name: String?,
    var user_destiny_name: String?,
    var user_destiny_bank_name: String?,
    var transaction_comision: Double,
    var transaction_day: String?,
    var transaction_month: String?,
    var transaction_sign: String?,
    var add_sub_value: Double,
    var transaction_balance_after: Double
) :Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readDouble()
    ) {
    }


    override fun toString(): String {
        return "${user_destiny_name}"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(num_account_source)
        parcel.writeString(num_account_destiny)
        parcel.writeString(transaction_concept)
        parcel.writeDouble(transaction_amount)
        parcel.writeString(user_source_name)
        parcel.writeString(user_source_bank_name)
        parcel.writeString(user_destiny_name)
        parcel.writeString(user_destiny_bank_name)
        parcel.writeDouble(transaction_comision)
        parcel.writeString(transaction_day)
        parcel.writeString(transaction_month)
        parcel.writeString(transaction_sign)
        parcel.writeDouble(add_sub_value)
        parcel.writeDouble(transaction_balance_after)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FirestoreTransaction> {
        override fun createFromParcel(parcel: Parcel): FirestoreTransaction {
            return FirestoreTransaction(parcel)
        }

        override fun newArray(size: Int): Array<FirestoreTransaction?> {
            return arrayOfNulls(size)
        }
    }

}