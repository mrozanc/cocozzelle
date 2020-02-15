plugins {
    id("kokozzelle-kotlin-config")
}

dependencies {
    api(enforcedPlatform(project(":kokozzelle-platform")))
    api(enforcedPlatform(project(":kokozzelle-dependencies")))
    implementation("com.fasterxml.jackson.core:jackson-databind")
    implementation("io.cucumber:cucumber-java8")
    implementation(kotlin("reflect"))
    implementation(kotlin("stdlib-jdk8"))

    testImplementation("com.willowtreeapps.assertk:assertk-jvm")
    testImplementation("org.spekframework.spek2:spek-dsl-jvm")
    testRuntimeOnly("org.spekframework.spek2:spek-runner-junit5")
}
