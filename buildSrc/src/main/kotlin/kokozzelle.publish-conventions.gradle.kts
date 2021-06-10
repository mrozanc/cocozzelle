import nu.studer.gradle.credentials.domain.CredentialsContainer

plugins {
    id("nu.studer.credentials")
    `maven-publish`
}

configure<PublishingExtension> {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/mrozanc/cocozzelle")
            credentials {
                username = project.getCredentials("gpr.user") ?: System.getenv("GPR_PACKAGE_USER")
                password = project.getCredentials("gpr.key") ?: System.getenv("GPR_PACKAGE_KEY")
            }
        }
    }
}

val configurePom: Action<MavenPom> = Action<MavenPom> {
    url.set("https://github.com/mrozanc/cocozzelle")
    licenses {
        license {
            name.set("The Apache License, Version 2.0")
            url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
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

plugins.withId("java") {
    project.configure<PublishingExtension> {
        publications {
            create<MavenPublication>("mavenJava") {
                from(components["java"])
                pom(configurePom)
            }
        }
    }
}

plugins.withId("java-platform") {
    project.configure<PublishingExtension> {
        publications {
            create<MavenPublication>("mavenJavaPlatform") {
                from(components["javaPlatform"])
                pom(configurePom)
            }
        }
    }
}

fun ExtensionAware.getCredentials(credentialsKey: String): String? {
    return (this.extensions.extraProperties["credentials"] as CredentialsContainer).propertyMissing(credentialsKey) as String?
}
