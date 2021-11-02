package com.codingCole.data.models.bodys

import kotlinx.serialization.Serializable
import org.mindrot.jbcrypt.BCrypt

@Serializable
data class UserCredentials(
    val username: String,
    val password: String
) {
    fun hashPassword(): String {
        return BCrypt.hashpw(password, BCrypt.gensalt())
    }

    fun isValidCredentials(): Boolean {
        return username.length >= 6 && password.length >= 6
    }
}
