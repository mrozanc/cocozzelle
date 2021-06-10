plugins {
    `java-platform`
    id("kokozzelle.publish-conventions")
}

dependencies {
    constraints {
        api(project(":kokozzelle-mapping"))
        api(project(":kokozzelle-core"))
    }
}
