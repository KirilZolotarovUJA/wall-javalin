plugins {
    kotlin("jvm") version "2.3.10"
    kotlin("plugin.jpa") version "2.3.10"
    application
}

group = "es.ujaen"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.h2database:h2:2.4.240")
    implementation("io.javalin:javalin-bundle:7.2.2")
    implementation("org.hibernate.orm:hibernate-core:7.3.5.Final")

    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(21)
}

application {
    mainClass = "es.ujaen.wall.MainKt"
}

tasks.test {
    useJUnitPlatform()
}
