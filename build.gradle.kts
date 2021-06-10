plugins {
    id("nu.studer.credentials")
    kotlin("jvm") apply false
    id("org.jetbrains.dokka") apply false
}

subprojects {
    repositories {
        mavenCentral()
    }
}
