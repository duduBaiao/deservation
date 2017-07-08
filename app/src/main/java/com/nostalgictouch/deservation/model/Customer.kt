package com.nostalgictouch.deservation.model

import android.os.Parcel
import android.os.Parcelable

data class Customer(
        val id: Int,
        val firstName: String,
        val lastName: String) : Parcelable {

    fun fullName(): String {
        return firstName + " " + lastName;
    }

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<Customer> = object : Parcelable.Creator<Customer> {
            override fun createFromParcel(source: Parcel): Customer = Customer(source)
            override fun newArray(size: Int): Array<Customer?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(
            source.readInt(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(id)
        dest.writeString(firstName)
        dest.writeString(lastName)
    }
}