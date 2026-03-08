plugins {
    id("java-library")
    id("pombo.spring-boot-service")
    kotlin("plugin.jpa")
}

group = "com.ttlabz"
version = "unspecified"

repositories {
    mavenCentral()
    maven { url = uri("https://repo.spring.io/milestone") }
    maven { url = uri("https://repo.spring.io/snapshot") }
}

dependencies {
    implementation(projects.common)

    implementation(libs.firebase.admin.sdk)

    implementation(libs.spring.boot.starter.mail)
    implementation(libs.spring.boot.starter.amqp)
    implementation(libs.spring.boot.starter.thymeleaf)

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}