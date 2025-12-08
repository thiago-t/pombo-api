package com.ttlabz.pombo.infra.database.repositories

import com.ttlabz.pombo.domain.type.UserId
import com.ttlabz.pombo.infra.database.entities.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<UserEntity, UserId> {
    fun findByEmail(email: String): UserEntity?
    fun findByEmailOrUsername(email: String, username: String): UserEntity?
}