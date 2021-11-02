package com.codingCole.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Note(
    val id: Int,
    val note: String
)
