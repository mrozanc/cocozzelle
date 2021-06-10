plugins {
    kotlin("jvm")
    id("kokozzelle.kotlin-conventions")
}

dependencies {
    api(platform(project(":kokozzelle-platform")))
    implementation(platform(project(":kokozzelle-dependencies")))
    implementation(libs.jackson.module.kotlin)
    implementation(libs.jackson.databind)
    implementation(libs.cucumber.java8)
    implementation(libs.groovy)
    implementation(libs.bundles.kotlin)

    testImplementation(libs.bundles.kotest)
}
