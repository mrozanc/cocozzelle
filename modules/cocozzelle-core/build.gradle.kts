plugins {
    id("cocozzelle-java-config")
}

dependencies {
    api(enforcedPlatform(project(":cocozzelle-platform")))
    implementation("com.fasterxml.jackson.core:jackson-databind")
    implementation("io.cucumber:cucumber-java8")

    testImplementation("org.assertj:assertj-core")
    testImplementation("org.junit.jupiter:junit-jupiter")
}
