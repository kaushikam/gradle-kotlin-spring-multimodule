plugins {
	kotlin("jvm")
	kotlin("plugin.spring")
	id("org.springframework.boot")
}

dependencies { 
	compile(kotlin("stdlib-jdk8"))
	implementation(project(":kotlin-data"))
	implementation(project(":kotlin-web"))
	implementation("org.springframework.boot:spring-boot-starter")
}