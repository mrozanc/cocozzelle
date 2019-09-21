plugins {
    id("cocozzelle-java-config")
}

dependencies {
    api(enforcedPlatform(project(":cocozzelle-platform")))
    implementation("io.cucumber:cucumber-java8")
}
