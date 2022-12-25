package com.example.githubuserapp

import android.os.Parcel
import android.os.Parcelable

data class User(
    var username: String??=null,
    var name: String??=null,
    var location: String??=null,
    var repository: String??=null,
    var company: String??=null,
    var item_follower: String??=null,
    var item_following: String??=null,
    var avatar: Int?=null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(username)
        parcel.writeString(name)
        parcel.writeString(location)
        parcel.writeString(repository)
        parcel.writeString(company)
        parcel.writeString(item_follower)
        parcel.writeString(item_following)
        parcel.writeValue(avatar)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }

}