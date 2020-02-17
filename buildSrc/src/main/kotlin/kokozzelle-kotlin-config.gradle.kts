import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    id("org.jetbrains.dokka")
    id("kokozzelle-publish-config")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<Test> {
    useJUnitPlatform {
        includeEngines("spek2")
    }
}

val dokkaJavadoc = tasks.register<DokkaTask>("dokkaJavadoc") {
    outputFormat = "javadoc"
    outputDirectory = "$buildDir/javadoc"
}

tasks.register<Jar>("javadocJar") {
    dependsOn(dokkaJavadoc)
    archiveClassifier.set("javadoc")
    from(dokkaJavadoc.map { it.outputDirectory })
}

configure<JavaPluginExtension> {
    withJavadocJar()
    withSourcesJar()
}
