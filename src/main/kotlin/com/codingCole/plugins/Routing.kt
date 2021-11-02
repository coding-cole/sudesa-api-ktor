package com.codingCole.plugins

import com.codingCole.routes.*
import io.ktor.routing.*
import io.ktor.http.content.*
import io.ktor.application.*

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
