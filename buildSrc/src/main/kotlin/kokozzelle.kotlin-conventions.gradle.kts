import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    id("org.jetbrains.dokka")
    id("kokozzelle.publish-conventions")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
    }
}

configurations {
    configureEach {
        if (name == "compileClasspath" || name.endsWith("CompileClasspath")
            || name == "runtimeClasspath" || name.endsWith("RuntimeClasspath")
            || name == "annotationProcessor" || name.endsWith("AnnotationProcessor")) {
            resolutionStrategy.failOnNonReproducibleResolution()
        }
    }
}

tasks.register("resolveAndLockAll") {
    doFirst {
        require(gradle.startParameter.isWriteDependencyLocks)
    }
    doLast {
        configurations.filter {
            // Add any custom filtering on the configurations to be resolved
            it.isCanBeResolved
        }.forEach { it.resolve() }
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.register<Jar>("javadocJar") {
    val dokkaJavadoc = tasks.named<DokkaTask>("dokkaJavadoc")
    dependsOn(dokkaJavadoc)
    archiveClassifier.set("javadoc")
    from(dokkaJavadoc.map { it.outputDirectory })
}

configure<JavaPluginExtension> {
    withJavadocJar()
    withSourcesJar()
}
