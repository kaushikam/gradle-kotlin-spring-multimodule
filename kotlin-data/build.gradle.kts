plugins {
	kotlin("jvm")
	kotlin("plugin.jpa")
	kotlin("plugin.spring")
	kotlin("plugin.allopen")
}

dependencies {
	implementation(kotlin("stdlib-jdk8"))
	implementation(kotlin("reflect"))
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("com.h2database:h2")
}