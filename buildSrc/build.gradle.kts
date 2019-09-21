plugins {
    `kotlin-dsl`
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}

repositories {
    jcenter()
}

val nebulaDependencyLockVersion: String by project
val nebulaPublishingVersion: String by project
dependencies {
    implementation("com.netflix.nebula:gradle-dependency-lock-plugin:$nebulaDependencyLockVersion")
    implementation("com.netflix.nebula:nebula-publishing-plugin:${nebulaPublishingVersion}")
}

