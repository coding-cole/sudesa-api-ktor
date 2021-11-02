package com.codingCole.data.models.dataClass

import kotlinx.serialization.Serializable

@Serializable
data class Student(
    val name: String,
    val matricNumber: String,
    val level: String,
    val cohort: String,
    val department: String,
    val email: String,
    val dob: String,
    val modeOfEntry: String,
    val imageProfile: String,
)
