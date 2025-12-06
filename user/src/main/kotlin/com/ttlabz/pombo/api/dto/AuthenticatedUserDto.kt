package com.ttlabz.pombo.api.dto

data class AuthenticatedUserDto(
    val user: UserDto,
    val accessToken: String,
    val refreshToken: String,
)
