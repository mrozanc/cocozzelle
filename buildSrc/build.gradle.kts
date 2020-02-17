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
    val credentialsPluginVersion: String by project
    val dokkaVersion: String by project
    val nebulaPublishingPluginVersion: String by project
    implementation("org.ajoberstar.grgit:grgit-core:3.1.1") {
        exclude(group = "org.codehaus.groovy", module = "groovy")
    }
    implementation("nu.studer:gradle-credentials-plugin:$credentialsPluginVersion")
    implementation("com.netflix.nebula:nebula-publishing-plugin:$nebulaPublishingPluginVersion")
    implementation("org.jetbrains.dokka:dokka-gradle-plugin:$dokkaVersion")
    implementation(kotlin("gradle-plugin", kotlinVersion))
}
