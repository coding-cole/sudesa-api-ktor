package com.codingCole.data.models.bodys

import kotlinx.serialization.Serializable

@Serializable
data class UserLoginInfo(
    val matricNumber: Int,
    val password: String
)
