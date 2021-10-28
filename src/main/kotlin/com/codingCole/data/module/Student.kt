package com.codingCole.data.module

import kotlinx.serialization.Serializable

@Serializable
data class Student(
    val name: String,
    val matricNumber: String,
    val cohort: String,
    val level: Int,
    val imgUrl: String
)
