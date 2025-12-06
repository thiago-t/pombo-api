package com.ttlabz.pombo.api.mappers

import com.ttlabz.pombo.api.dto.AuthenticatedUserDto
import com.ttlabz.pombo.api.dto.UserDto
import com.ttlabz.pombo.domain.model.AuthenticatedUser
import com.ttlabz.pombo.domain.model.User

fun AuthenticatedUser.toAuthenticatedUserDto(): AuthenticatedUserDto {
    return AuthenticatedUserDto(
        user = user.toUserDto(),
        accessToken = accessToken,
        refreshToken = refreshToken
    )
}

fun User.toUserDto(): UserDto {
    return UserDto(
        id = id,
        email = email,
        username = username,
        hasVerifiedEmail = hasEmailVerified
    )
}