package com.hera.users.data.network

import com.hera.users.data.models.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {

    @GET("api/users")
    suspend fun getUsers(
        @Query("page")
        page: Int
    ): UserResponse
}