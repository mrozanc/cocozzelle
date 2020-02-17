import nu.studer.gradle.credentials.domain.CredentialsContainer

plugins {
    id("nu.studer.credentials")
    id("nebula.maven-publish")
}

configure<PublishingExtension> {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/mrozanc/cocozzelle")
            credentials {
                username = project.getCredentials("gpr.user")
                password = project.getCredentials("gpr.key")
            }
        }
    }
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

fun Project.getCredentials(credentialsKey: String): String? {
    return (this.extensions.extraProperties["credentials"] as CredentialsContainer).propertyMissing(credentialsKey) as String?
}
