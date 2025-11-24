package com.ttlabz.pombo.api.controllers

import com.ttlabz.com.ttlabz.pombo.api.dto.RefreshRequest
import com.ttlabz.pombo.api.dto.AuthenticatedUserDto
import com.ttlabz.pombo.api.dto.LoginRequest
import com.ttlabz.pombo.api.dto.RegisterRequest
import com.ttlabz.pombo.api.dto.UserDto
import com.ttlabz.pombo.api.mappers.toAuthenticatedUserDto
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

    @PostMapping("/login")
    fun login(
        @RequestBody body: LoginRequest
    ): AuthenticatedUserDto {
        return authService.login(
            email = body.email,
            password = body.password
        ).toAuthenticatedUserDto()
    }

    @PostMapping("/refresh")
    fun refresh(
        @RequestBody body: RefreshRequest
    ): AuthenticatedUserDto {
        return authService
            .refresh(body.refreshToken)
            .toAuthenticatedUserDto()
    }

    @PostMapping("/logout")
    fun logout(
        @RequestBody body: RefreshRequest
    ) {
        authService.logout(body.refreshToken)
    }

}