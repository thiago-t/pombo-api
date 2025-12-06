package com.ttlabz.pombo

import com.ttlabz.pombo.infra.database.entities.UserEntity
import com.ttlabz.pombo.infra.database.repositories.UserRepository
import jakarta.annotation.PostConstruct
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Component

@SpringBootApplication
class PomboApplication

fun main(args: Array<String>) {
    runApplication<PomboApplication>(*args)
}

@Component
class Demo(
    private val repository: UserRepository
) {

    @PostConstruct
    fun init() {
//        repository.save(
//            UserEntity(
//                email = "annaortiz@mail.com",
//                username = "Anna",
//                hashedPassword = "321"
//            )
//        )
    }
}