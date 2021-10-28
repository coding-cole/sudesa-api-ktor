package com.codingCole.plugins

import com.codingCole.routes.home
import com.codingCole.routes.randomStudent
import io.ktor.routing.*
import io.ktor.http.content.*
import io.ktor.application.*

fun Application.configureRouting() {
    routing {
        randomStudent()
        home()

        // Static plugin. Try to access `/static/index.html`
        static {
            resources("static")
        }
    }
}
