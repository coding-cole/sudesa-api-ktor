package com.codingCole.utils

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.codingCole.data.models.dataClass.User
import io.ktor.config.*
import java.util.*

class TokenManager(config: HoconApplicationConfig) {

    val audience = config.property("audience").getString()
    val secret = config.property("secret").getString()
    val issuer = config.property("issuer").getString()
    val expirationDate = System.currentTimeMillis() + 600000


    fun generateJWTToken(user: User): String {

        return JWT.create()
            .withAudience(audience)
            .withIssuer(issuer)
            .withClaim("username", user.username)
            .withClaim("userId", user.id)
            .withExpiresAt(Date(expirationDate))
            .sign(Algorithm.HMAC256(secret))
    }

    fun verifyJWTToken(): JWTVerifier {
        return JWT.require(Algorithm.HMAC256(secret))
            .withAudience(audience)
            .withIssuer(issuer)
            .build()
    }
}