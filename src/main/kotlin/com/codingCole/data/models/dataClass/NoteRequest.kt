package com.codingCole.data.models

import kotlinx.serialization.Serializable

@Serializable
data class NoteRequest(
    val note: String
)
