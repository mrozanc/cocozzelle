plugins {
    `java-platform`
    id("kokozzelle.publish-conventions")
}

javaPlatform {
    allowDependencies()
}

dependencies {
    constraints {
        api(libs.bundles.kotest)
    }

    api(platform(libs.junit.jupiter.bom.get()))
    api(platform(libs.kotlin.bom.get()))
    api(platform(libs.jackson.bom.get()))
    api(platform(libs.cucumber.jvm.get()))
}
