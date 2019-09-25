plugins {
    `java-platform`
    id("cocozzelle-publish-config")
}

javaPlatform {
    allowDependencies()
}

dependencies {
    val jacksonBomVersion: String by project
    api(enforcedPlatform("com.fasterxml.jackson:jackson-bom:${jacksonBomVersion}"))

    constraints {
        api(project(":cocozzelle-core"))

        val assertjVersion: String by project
        val cucumberVersion: String by project
        val junitJupiterVersion: String by project
        api("io.cucumber:cucumber-java8:$cucumberVersion")
        api("io.cucumber:cucumber-java:$cucumberVersion")
        api("org.assertj:assertj-core:${assertjVersion}")
        api("org.junit.jupiter:junit-jupiter:${junitJupiterVersion}")
    }

}
