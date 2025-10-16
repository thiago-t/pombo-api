package com.ttlabz.pombo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PomboApplication

fun main(args: Array<String>) {
	runApplication<PomboApplication>(*args)
}
