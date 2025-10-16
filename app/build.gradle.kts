plugins {
	id("pombo.spring-boot-app")
}

group = "com.ttlabz"
version = "0.0.1-SNAPSHOT"
description = "Pombo Backend"

dependencies {
	implementation(projects.user)
	implementation(projects.chat)
	implementation(projects.notification)
	implementation(projects.common)
}