package com.codingCole.plugins

import com.codingCole.utils.TokenManager
import com.typesafe.config.ConfigFactory
import io.ktor.features.*
import org.slf4j.event.*
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import io.ktor.config.*
import io.ktor.response.*
import io.ktor.request.*

fun Application.configureAuthentication() {

    val config = HoconApplicationConfig(ConfigFactory.load())
    val tokenManager = TokenManager(config)

    install(Authentication) {
        jwt {
            verifier(tokenManager.verifyJWTToken())
            realm = config.property("realm").getString()
            validate { jwtCredential ->
                if (jwtCredential.payload.getClaim("username").asString().isNotEmpty()) {
                    JWTPrincipal(jwtCredential.payload)
                } else {
                    null
                }
            }
        }
    }
}
