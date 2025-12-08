package com.ttlabz.pombo.domain.model

import com.ttlabz.pombo.domain.type.UserId

data class User(
    val id: UserId,
    val username: String,
    val email: String,
    val hasEmailVerified: Boolean,
)
