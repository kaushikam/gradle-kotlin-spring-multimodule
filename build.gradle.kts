import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	base
	kotlin("jvm") version ("1.3.61") apply false
	kotlin("plugin.spring") version ("1.3.61") apply false
	kotlin("plugin.jpa") version ("1.3.61") apply false
	kotlin("plugin.allopen") version ("1.3.61") apply false
	id("org.springframework.boot") version ("2.2.1.RELEASE") apply false
	id("io.spring.dependency-management") version ("1.0.8.RELEASE")
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