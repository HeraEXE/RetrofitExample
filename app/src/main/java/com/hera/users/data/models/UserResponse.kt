package com.hera.users.data.models

import com.hera.users.data.models.Data
import com.hera.users.data.models.Support

data class UserResponse(
    val data: List<Data>,
    val page: Int,
    val per_page: Int,
    val support: Support,
    val total: Int,
    val total_pages: Int
)