package com.ttlabz.pombo.api.dto

import com.ttlabz.pombo.domain.model.UserId

data class UserDto(
    val id: UserId,
    val email: String,
    val username: String,
    val hasVerifiedEmail: Boolean
)
