plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
}

dependencies {
    val kotlinVersion: String by project
    val credentialsPluginVersion: String by project
    val dokkaVersion: String by project
    implementation("nu.studer:gradle-credentials-plugin:$credentialsPluginVersion")
    implementation("org.jetbrains.dokka:dokka-gradle-plugin:$dokkaVersion")
    implementation(kotlin("gradle-plugin", kotlinVersion))
    implementation(gradleApi())
    implementation(embeddedKotlin("stdlib-jdk8"))
}
