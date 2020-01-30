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

	ext["snakeyaml.version"] = "1.24"

	dependencyManagement {
		imports {
			mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
		}

		dependencies {
			dependencySet("org.junit.jupiter:5.6.0") {
				entry("junit-jupiter")
				entry("junit-jupiter-api")
				entry("junit-jupiter-engine")
				entry("junit-jupiter-params")
			}
			dependencySet("org.junit.platform:1.6.0") {
				entry("junit-platform-commons")
				entry("junit-platform-engine")
			}
		}
	}

	tasks.withType<Test> {
		useJUnitPlatform()
	}

	dependencies {
		val implementation by configurations
		implementation("com.google.guava:guava:27.1-jre")
	}

	extra["execProfile"] = {
		val buildProfile: String by project
		val profileFileRelativePath = "profiles/profile-$buildProfile.gradle.kts"
		val profileFileAbsolutePath: String = file(".")
			.absoluteFile.resolve(profileFileRelativePath).absolutePath
		profileFileAbsolutePath
	}
	extra.set("getConfig", {
		val configFileRelativePath = "profiles/common.gradle.kts"
		file(".").absoluteFile.resolve(configFileRelativePath).absolutePath
	})

}

dependencies { 
	subprojects.forEach { 
		archives(it)
	}
}
