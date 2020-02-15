plugins {
    `java-platform`
    id("kokozzelle-publish-config")
}

javaPlatform {
    allowDependencies()
}

dependencies {
    constraints {
        val assertkVersion: String by project
        val cucumberVersion: String by project
        val spekVersion: String by project
        api("com.willowtreeapps.assertk:assertk-jvm:$assertkVersion")
        api("io.cucumber:cucumber-java8:$cucumberVersion")
        api("io.cucumber:cucumber-java:$cucumberVersion")
        api("org.spekframework.spek2:spek-dsl-jvm:$spekVersion")
        api("org.spekframework.spek2:spek-runner-junit5:$spekVersion")
    }

    val jacksonBomVersion: String by project
    val junitJupiterVersion: String by project
    val kotlinVersion: String by project
    api(enforcedPlatform(kotlin("bom", kotlinVersion)))
    api(enforcedPlatform("com.fasterxml.jackson:jackson-bom:$jacksonBomVersion"))
    api(enforcedPlatform("org.junit:junit-bom:$junitJupiterVersion"))
}
