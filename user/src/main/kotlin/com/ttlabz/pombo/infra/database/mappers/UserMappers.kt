package com.ttlabz.pombo.infra.database.mappers

import com.ttlabz.pombo.domain.model.User
import com.ttlabz.pombo.infra.database.entities.UserEntity

fun UserEntity.toUser(): User {
    return User(
        id = id!!,
        username = username,
        email = email,
        hasEmailVerified = hasVerifiedEmail
    )
}