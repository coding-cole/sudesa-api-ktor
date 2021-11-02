package com.codingCole.data.models.bodys

import kotlinx.serialization.Serializable

@Serializable
data class StudentCredentials(
    val matricNumber: Int,
    val password: String
)
