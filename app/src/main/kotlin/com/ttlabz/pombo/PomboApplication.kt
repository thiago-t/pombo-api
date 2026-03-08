package com.ttlabz.pombo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class PomboApplication

fun main(args: Array<String>) {
    runApplication<PomboApplication>(*args)
}