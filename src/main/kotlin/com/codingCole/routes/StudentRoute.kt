package com.codingCole.routes

import com.codingCole.data.module.Student
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

private const val BASE_URL1 = "https://localhost:8080"
private const val BASE_URL2 = "https://studesa-app-api.herokuapp.com"

private val students = listOf(
    Student("Jamal", "170313001", "Mathematics Education",
        300, "$BASE_URL2/images/profile_img.png" ),
    Student("Jamal", "170313002", "Mathematics Education",
        300, "$BASE_URL2/images/profile_img.png" ),
    Student("Jamal", "170313003", "Mathematics Education",
        300, "$BASE_URL2/images/profile_img.png" ),
    Student("Jamal", "170313004", "Mathematics Education",
        300, "$BASE_URL2/images/profile_img.png" )
)

fun Route.randomStudent() {
    get("/randomStudent") {
//        call.parameters["studentId"]
        call.respond(
            HttpStatusCode.OK,
            students.random()
        )
    }
}

fun Route.home() {
    get("/") {
        call.respondText("And, trust me...it works perfectly!")
    }
}