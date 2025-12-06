package com.ttlabz.pombo.infra.security

import com.ttlabz.pombo.domain.exception.InvalidPasswordException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

@Component
class PasswordEncoder {

    private val bcrypt = BCryptPasswordEncoder()

    fun encode(rawPassword: String): String = bcrypt.encode(rawPassword) ?: throw InvalidPasswordException()

    fun matches(rawPassword: String, hashedPassword: String): Boolean {
        return bcrypt.matches(rawPassword, hashedPassword)
    }

}