package com.ttlabz.pombo.domain.exception

class InvalidTokenException(
    override val message: String?
) : RuntimeException(
    message ?: "Invalid Token"
)