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
    val lombokPluginVersion: String by project
    val nebulaDependencyLockPluginVersion: String by project
    val nebulaPublishingPluginVersion: String by project
    implementation("com.netflix.nebula:gradle-dependency-lock-plugin:$nebulaDependencyLockPluginVersion")
    implementation("com.netflix.nebula:nebula-publishing-plugin:${nebulaPublishingPluginVersion}")
    implementation("io.freefair.gradle:lombok-plugin:${lombokPluginVersion}")
}

