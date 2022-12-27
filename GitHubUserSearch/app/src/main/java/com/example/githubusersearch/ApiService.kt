package com.example.githubusersearch

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    @Headers("Authorization: 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")
    fun getSearchUsers(
        @Query("q") query: String
    ): Call <UserResponse>
}