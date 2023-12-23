package com.tegar.suitmediatest1.data.remote.retrofit

import com.tegar.suitmediatest1.data.remote.response.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("users")
    suspend fun getUserData(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ) : UserResponse
}