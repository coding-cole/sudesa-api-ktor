package com.codingCole.routes

import com.codingCole.data.entities.UserEntity
import com.codingCole.data.models.bodys.UserCredentials
import com.codingCole.data.models.dataClass.User
import com.codingCole.data.models.responses.StatusResponse
import com.codingCole.db.DataBaseConnection
import com.codingCole.utils.TokenManager
import com.typesafe.config.ConfigFactory
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import io.ktor.config.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.ktorm.dsl.*
import org.mindrot.jbcrypt.BCrypt
import java.util.*

private val db = DataBaseConnection.database
private val tokenManager = TokenManager(HoconApplicationConfig(ConfigFactory.load()))

fun Route.authRouter() {
    register()
    login()
    getToken()
}

private fun Route.register() {
    post("/register") {
        val userCredentials = call.receive<UserCredentials>()

        if (!userCredentials.isValidCredentials()) {
            call.respond(HttpStatusCode.BadRequest,
            StatusResponse(success = false,
            data = "Username and password dhould be greater than 6 "))
            return@post
        }

        val username = userCredentials.username.lowercase(Locale.getDefault())
        val password = userCredentials.hashPassword()

        //check if username exists
        val user = db.from(UserEntity)
            .select()
            .where { UserEntity.username eq username }
            .map { it[UserEntity.username] }
            .firstOrNull()
        if (user != null) {
            call.respond(
                HttpStatusCode.BadRequest,
                StatusResponse(success = false, data = "User already exist, try another")
            )
            return@post
        }

        db.insert(UserEntity) {
            set(it.username, username)
            set(it.password, password)
        }
        call.respond(
            HttpStatusCode.Created,
            StatusResponse(success = true, data = "User created successfully")
        )
    }
}

private fun Route.login() {
    post("/login") {
        val userCredentials = call.receive<UserCredentials>()

        if (!userCredentials.isValidCredentials()) {
            call.respond(HttpStatusCode.BadRequest,
                StatusResponse(success = false,
                    data = "Username and password should be greater than 6 "))
            return@post
        }

        val username = userCredentials.username.lowercase(Locale.getDefault())
        val password = userCredentials.password

        //check if user exists
        val user = db.from(UserEntity)
            .select()
            .where { UserEntity.username eq username }
            .map {
                val id = it[UserEntity.id]!!
                val username = it[UserEntity.username]!!
                val password = it[UserEntity.password]!!
                User(id, username, password)
            }.firstOrNull()

        if (user == null) {
            call.respond(
                HttpStatusCode.BadRequest,
                StatusResponse(success = false, data = "Invalid username or password")
            )
            return@post
        }

        val doesPasswordMatch = BCrypt.checkpw(password, user.password)
        if (!doesPasswordMatch) {
            call.respond(
                HttpStatusCode.BadRequest,
                StatusResponse(success = false, data = "Invalid username or password")
            )
            return@post
        }

        val token = tokenManager.generateJWTToken(user)
        call.respond(
            HttpStatusCode.OK,
            StatusResponse(success = true, data = token)
        )
    }
}

fun Route.getToken() {
    authenticate {
        get("/me") {
            val principal = call.principal<JWTPrincipal>()
            val username = principal!!.payload.getClaim("username").asString()
            val userId = principal.payload.getClaim("userId").asInt()
            call.respondText("Hello, $username with the id: $userId")
        }
    }
}