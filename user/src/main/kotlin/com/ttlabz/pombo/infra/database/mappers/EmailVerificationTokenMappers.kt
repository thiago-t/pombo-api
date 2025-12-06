package com.ttlabz.pombo.infra.database.mappers

import com.ttlabz.pombo.domain.model.EmailVerificationToken
import com.ttlabz.pombo.infra.database.entities.EmailVerificationTokenEntity

fun EmailVerificationTokenEntity.toEmailVerificationToken(): EmailVerificationToken {
    return EmailVerificationToken(
        id = id,
        token = token,
        user = user.toUser()
    )
}