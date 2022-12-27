package com.example.githubusersearch

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class UserResponse(

	@field:SerializedName("items")
	val items: ArrayList<Item>
)


