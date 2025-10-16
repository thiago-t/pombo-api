pluginManagement {
	repositories {
		maven { url = uri("https://repo.spring.io/snapshot") }
		gradlePluginPortal()
	}
}
rootProject.name = "pombo"

include("app")
include("user")
include("chat")
include("notification")
include("common")
