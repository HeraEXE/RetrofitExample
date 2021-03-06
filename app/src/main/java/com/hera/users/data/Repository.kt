package com.hera.users.data

import com.hera.users.data.models.UserResponse
import com.hera.users.data.network.RetrofitInstance

object Repository {

    suspend fun getUser(page: Int): UserResponse {
        return RetrofitInstance.api.getUsers(page)
    }
}