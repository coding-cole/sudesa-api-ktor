package com.codingCole.data.models.dataClass

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Int,
    val username: String,
    val password: String
)
