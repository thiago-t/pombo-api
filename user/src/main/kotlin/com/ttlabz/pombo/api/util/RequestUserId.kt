package com.ttlabz.pombo.api.util

import com.ttlabz.pombo.domain.exception.UnauthorizedException
import com.ttlabz.pombo.domain.model.UserId
import org.springframework.security.core.context.SecurityContextHolder

val requestUserId: UserId
    get() = SecurityContextHolder.getContext().authentication?.principal as? UserId
        ?: throw UnauthorizedException()
