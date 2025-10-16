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

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}