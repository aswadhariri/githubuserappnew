package com.example.githubusersearch

import com.google.gson.annotations.SerializedName

data class Item(

    @field:SerializedName("avatar_url")
    val avatarUrl: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("login")
    val login: String
)
