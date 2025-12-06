package com.ttlabz.pombo.domain.model

data class EmailVerificationToken(
    val id: Long,
    val token: String,
    val user: User
)
