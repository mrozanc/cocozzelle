plugins {
    `java-platform`
    id("kokozzelle-publish-config")
}

dependencies {
    constraints {
        api(project(":kokozzelle-core"))
    }
}
