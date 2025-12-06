package com.ttlabz.pombo.api.controllers

import com.ttlabz.pombo.api.dto.RegisterRequest
import com.ttlabz.pombo.api.dto.UserDto
import com.ttlabz.pombo.api.mappers.toUserDto
import com.ttlabz.pombo.service.auth.AuthService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/auth")
class AuthController(
    private val authService: AuthService
) {

    @PostMapping("/register")
    fun register(
        @Valid @RequestBody body: RegisterRequest
    ): UserDto {
        return authService.register(
            email = body.email,
            username = body.username,
            password = body.password
        ).toUserDto()
    }

}