import nebula.plugin.publishing.publications.JavadocJarPlugin
import nebula.plugin.publishing.publications.SourceJarPlugin

plugins {
    `java-library`
    id("io.freefair.lombok")
    id("nebula.dependency-lock")
}

apply<JavadocJarPlugin>()
apply<SourceJarPlugin>()

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

lombok {
    val lombokVersion: String by project
    version.set(lombokVersion)
}

tasks.withType<Test> {
    useJUnitPlatform()
}
