plugins {
    id("nebula.maven-publish")
}

plugins.withId("java-platform") {
    project.configure<PublishingExtension> {
        publications {
            named<MavenPublication>("nebula") {
                from(components["javaPlatform"])
            }
        }
    }
}
