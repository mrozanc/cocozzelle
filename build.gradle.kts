plugins {
    id("nebula.release")
}

subprojects {
    repositories {
        jcenter()
    }
    apply(plugin = "nebula.info-scm")
    apply(plugin = "kokozzelle-publish-config")

    configure<PublishingExtension> {
        publications {
            named<MavenPublication>("nebula") {
                pom {
                    url.set("https://github.com/mrozanc/cocozzelle")
                    licenses {
                        license {
                            name.set("The Apache License, Version 2.0")
                            url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                        }
                    }
                    developers {
                        developer {
                            id.set("mrozanc")
                            name.set("Marc ROZANC")
                            email.set("marc@rozanc.fr")
                            roles.add("owner")
                        }
                    }
                }
            }
        }
    }
}

tasks.withType<Wrapper> {
    gradleVersion = "6.1.1"
    distributionType = Wrapper.DistributionType.ALL
}
