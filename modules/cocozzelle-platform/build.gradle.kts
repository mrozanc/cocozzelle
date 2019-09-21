plugins {
    `java-platform`
    id("cocozzelle-publish-config")
}

dependencies {
    constraints {
        api(project(":cocozzelle-core"))

        val cucumberVersion = "4.7.2"
        api("io.cucumber:cucumber-java8:$cucumberVersion")
        api("io.cucumber:cucumber-java:$cucumberVersion")
    }

}
