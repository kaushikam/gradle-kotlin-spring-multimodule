import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	base
	kotlin("jvm") version (Dependencies.kotlin_version) apply false
	kotlin("plugin.spring") version (Dependencies.kotlin_version) apply false
	kotlin("plugin.jpa") version (Dependencies.kotlin_version) apply false
	kotlin("plugin.allopen") version (Dependencies.kotlin_version) apply false
	id("org.springframework.boot") version (Dependencies.spring_plugin_version) apply false
	id("io.spring.dependency-management") version (Dependencies.spring_plugin_mgmt_version)
}

allprojects {
	group = "com.kaushikam.example"
	version = "0.0.1"

	repositories {
		jcenter()
		mavenCentral()
	}
}

subprojects {
	apply(plugin="org.jetbrains.kotlin.jvm")
	apply(plugin="io.spring.dependency-management")

	tasks.withType<KotlinCompile>().configureEach {
		kotlinOptions {
			freeCompilerArgs = listOf("-Xjsr305=strict")
			jvmTarget = "1.8"
		}
	}

	dependencyManagement {
		imports {
			mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
		}
	}

	tasks.withType<Test> {
		useJUnitPlatform()
	}

	dependencies {
		val testImplementation by configurations
		testImplementation("org.junit.jupiter:junit-jupiter")
	}
}

dependencies { 
	subprojects.forEach { 
		archives(it)
	}
}