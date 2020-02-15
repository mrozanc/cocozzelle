plugins {
    `kotlin-dsl`
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}

repositories {
    gradlePluginPortal()
    jcenter()
}

dependencies {
    val kotlinVersion: String by project
    val dokkaVersion: String by project
    val nebulaPublishingPluginVersion: String by project
    implementation("com.netflix.nebula:nebula-publishing-plugin:${nebulaPublishingPluginVersion}")
    implementation("org.jetbrains.dokka:dokka-gradle-plugin:${dokkaVersion}")
    implementation(kotlin("gradle-plugin", kotlinVersion))
}
