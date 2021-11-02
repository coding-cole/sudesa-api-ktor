package com.codingCole.data.models.dataClass

import kotlinx.serialization.Serializable

@Serializable
data class Note(
    val id: Int,
    val note: String
)
