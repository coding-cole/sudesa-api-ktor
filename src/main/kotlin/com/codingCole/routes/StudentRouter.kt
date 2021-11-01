package com.codingCole.routes

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.notes() {
    get("/notes") {
        call.respondText("All soo fine")
    }
}