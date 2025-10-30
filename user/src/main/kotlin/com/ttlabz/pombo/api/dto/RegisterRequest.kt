package com.ttlabz.pombo.api.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Pattern
import org.hibernate.validator.constraints.Length

data class RegisterRequest(
    @field:Email(message = "Must be a valid email address")
    val email: String,
    @field:Length(min = 3, max = 20, message = "Email length must be between 3 and 20 characters")
    val username: String,
    @field:Pattern(
        regexp = "^[a-zA-Z0-9]{8,}\$",
        message = "Password must be at least 8 characters"
    )
    val password: String
)
