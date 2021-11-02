package com.codingCole.data.models.dataClass

import kotlinx.serialization.Serializable

@Serializable
data class NoteRequest(
    val note: String
)
