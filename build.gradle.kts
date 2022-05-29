import org.gradle.api.tasks.testing.logging.TestLogEvent.*

plugins {
	java
	id("io.freefair.lombok")
	id("org.springframework.boot")
}

repositories {
	mavenLocal()
	mavenCentral()
}

springBoot {
	buildInfo()
}

val springBootVersion: String by project
val kafkaVersion: String by project
val lombokVersion: String by project
val swaggerFoxVersion: String by project
val postgresqlVersion: String by project
val hibernateVersion: String by project

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web:$springBootVersion")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa:$springBootVersion")
	implementation("org.springframework.boot:spring-boot-starter-validation:$springBootVersion")
	implementation("org.hibernate:hibernate-core:$hibernateVersion")
	implementation("org.hibernate:hibernate-entitymanager:$hibernateVersion")
	implementation("io.springfox:springfox-boot-starter:$swaggerFoxVersion")
	implementation("org.projectlombok:lombok:$lombokVersion")
	implementation("org.postgresql:postgresql:$postgresqlVersion")
	testImplementation("org.springframework.boot:spring-boot-starter-test:$springBootVersion")
}

tasks {
	withType<JavaCompile> {
		options.encoding = "UTF-8"
		sourceCompatibility = JavaVersion.VERSION_11.toString()
		targetCompatibility = JavaVersion.VERSION_11.toString()
	}
}

sourceSets {
	create("generated") {
		compileClasspath += sourceSets["main"].compileClasspath
		runtimeClasspath += sourceSets["main"].runtimeClasspath
	}
	create("test-integration") {
		java.srcDir("src/test-integration/java")
		resources.srcDir("src/test-integration/resources")
		compileClasspath += sourceSets["main"].output + configurations["testRuntimeClasspath"]
		runtimeClasspath += output + compileClasspath + sourceSets["test"].runtimeClasspath
	}
}

tasks.withType<Jar> {
	manifest {
		attributes["Main-Class"] = "ru.bashsu.mobile.MobileApplication"
	}
}

task<Test>("test-integration") {
	description = "Runs the integration tests"
	group = "verification"
	testClassesDirs = sourceSets["test-integration"].output.classesDirs
	classpath = sourceSets["test-integration"].runtimeClasspath
	useJUnitPlatform()
}

gradle.taskGraph.whenReady {
	allTasks
		.filter { it.hasProperty("duplicatesStrategy") } // Because it's some weird decorated wrapper that I can't cast.
		.forEach {
			it.setProperty("duplicatesStrategy", "EXCLUDE")
		}
}