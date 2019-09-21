plugins {
    id("nebula.maven-publish")
}

plugins.withId("java-platform") {
    project.publishing {
        publications {
            named<MavenPublication>("nebula") {
                from(components["javaPlatform"])
            }
        }
    }
}
