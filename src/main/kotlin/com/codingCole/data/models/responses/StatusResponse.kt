package com.codingCole.data.models.responses

import kotlinx.serialization.Serializable

@Serializable
data class StatusResponse<T>(
    val data: T,
    val success: Boolean
)
