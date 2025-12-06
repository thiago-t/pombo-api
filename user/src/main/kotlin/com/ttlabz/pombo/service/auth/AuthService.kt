package com.ttlabz.pombo.service.auth

import com.ttlabz.pombo.domain.exception.UserAlreadyExistsException
import com.ttlabz.pombo.infra.database.mappers.toUser
import com.ttlabz.pombo.domain.model.User
import com.ttlabz.pombo.infra.database.entities.UserEntity
import com.ttlabz.pombo.infra.database.repositories.UserRepository
import com.ttlabz.pombo.infra.security.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
) {

    fun register(email: String, username: String, password: String): User {
        val user = userRepository.findByEmailOrUsername(
            email = email.trim(),
            username = username.trim()
        )

        if (user != null) {
            throw UserAlreadyExistsException()
        }

        val savedUser = userRepository.save(
            UserEntity(
                email = email.trim(),
                username = username.trim(),
                hashedPassword = passwordEncoder.encode(password)
            )
        ).toUser()

        return savedUser
    }

}