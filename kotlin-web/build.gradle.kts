plugins {
	kotlin("jvm")
	kotlin("plugin.spring")
}

dependencies {
	implementation(project(":kotlin-data"))
	compile(kotlin("stdlib-jdk8"))
	implementation("org.springframework.boot:spring-boot-starter-web")
}