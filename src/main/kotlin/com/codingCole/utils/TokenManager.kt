package com.codingCole.utils

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.codingCole.data.models.dataClass.User
import io.ktor.config.*

class TokinManager(val config: HoconApplicationConfig) {

    fun generateJWTToken(user: User): String {
        val audience = config.property("audience").getString()
        val secret = config.property("secret").getString()
        val issuer = config.property("issuer").getString()
        val expirationDate = System.currentTimeMillis() + 60000

        return JWT.create()
            .withAudience(audience)
            .withIssuer(issuer)
            .withClaim("username", user.username)
            .withClaim("userId", user.id)
            .sign(Algorithm.HMAC256(secret))
    }
}