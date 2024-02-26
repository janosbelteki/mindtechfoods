val springBootVersion = "2.5.12"
val kotlinVersion = "1.9.22"

buildscript {
    repositories {
        mavenCentral()
    }

    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:2.5.12")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.22")
        classpath("org.jetbrains.kotlin:kotlin-allopen:1.9.22")
    }
}

plugins {
    id("org.jetbrains.kotlin.jvm") version "1.9.22"
    id("org.springframework.boot") version "2.5.12"
}

apply {
    plugin("java")
    plugin("kotlin")
    plugin("kotlin-spring")
    plugin("idea")
    plugin("io.spring.dependency-management")
}

group = "com.mindtechapps"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    testImplementation("org.jetbrains.kotlin:kotlin-test:$kotlinVersion")

    implementation("javax.inject:javax.inject:1")
    implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
    implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")

    implementation("org.springframework.boot:spring-boot-starter-web:$springBootVersion")
    implementation("org.springframework.boot:spring-boot-starter-actuator:$springBootVersion")
    implementation("org.springframework.security:spring-security-core:6.2.1")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:$springBootVersion")

    implementation("javax.validation:validation-api:2.0.1.Final")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.15.3")

    //implementation("org.ktorm:ktorm-core:3.6.0")
    //implementation("org.ktorm:ktorm-support-postgresql:3.6.0")
    implementation("org.postgresql:postgresql:42.7.2")

    //implementation("com.github.kostyan.bcprov:bcprov-jdk15on:1.68")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}
