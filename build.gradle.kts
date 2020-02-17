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

tasks.named("finalSetup") {
    doLast {
        val git = org.ajoberstar.grgit.Grgit.open()
        val releaseBranch = git.branch.current()
        println("git fetch origin master:master")
        git.fetch {
            remote = "origin"
            refSpecs = listOf("+refs/heads/master:refs/heads/master")
        }
        println("git checkout master")
        git.checkout {
            branch = "master"
        }
        println("git merge --no-ff ${releaseBranch.name}")
        git.merge {
            head = releaseBranch.name
            setMode("create-commit")
            message = "REL v${project.version}"
        }
    }
}

tasks.named("release") {
    dependsOn(project.getTasksByName("build", true))
}

tasks.named("postRelease") {
    dependsOn(project.getTasksByName("publish", true))
//    doLast {
//        val git = org.ajoberstar.grgit.Grgit.open()
//        println("git push origin master")
//        git.push {
//            remote = "origin"
//            refsOrSpecs = listOf("refs/heads/master:refs/heads/master")
//        }
//    }
}

tasks.withType<Wrapper> {
    gradleVersion = "6.1.1"
    distributionType = Wrapper.DistributionType.ALL
}
