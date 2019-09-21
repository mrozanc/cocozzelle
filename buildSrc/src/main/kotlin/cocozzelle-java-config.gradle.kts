import nebula.plugin.publishing.publications.JavadocJarPlugin
import nebula.plugin.publishing.publications.SourceJarPlugin

plugins {
    `java-library`
    id("nebula.dependency-lock")
}

apply<JavadocJarPlugin>()
apply<SourceJarPlugin>()

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

tasks.withType<Test> {
    useJUnitPlatform()
}
