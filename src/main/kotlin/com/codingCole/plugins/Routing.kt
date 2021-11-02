package com.codingCole.plugins

import com.codingCole.routes.*
import io.ktor.routing.*
import io.ktor.http.content.*
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import io.ktor.request.*
import io.ktor.response.*

fun Application.configureRouting() {
    routing {
        noteRouter()
        authRouter()

        // Static plugin. Try to access `/static/index.html`
        static {
            resources("static")
        }
    }
}
