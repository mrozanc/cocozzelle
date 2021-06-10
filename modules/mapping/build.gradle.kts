plugins {
    kotlin("jvm")
    id("kokozzelle.kotlin-conventions")
}

dependencies {
    implementation(platform(project(":kokozzelle-platform")))
    implementation(platform(project(":kokozzelle-dependencies")))
    implementation(libs.jackson.dataformat.properties)
    implementation(libs.jackson.module.kotlin)
    implementation(libs.jackson.databind)
    implementation(libs.cucumber.java)
    implementation(libs.groovy)
    implementation(libs.bundles.kotlin)

    testImplementation(libs.bundles.kotest)
}
