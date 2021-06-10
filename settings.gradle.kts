enableFeaturePreview("VERSION_CATALOGS")

rootProject.name = "kokozzelle"

file("$rootDir/modules").listFiles { f ->
    f.isDirectory && f.listFiles { bf ->
        bf.isFile && listOf("build.gradle", "build.gradle.kts").contains(bf.name) }?.isNotEmpty() ?: false
}?.forEach {
    include(":${rootProject.name}-${it.name}")
    project(":${rootProject.name}-${it.name}").projectDir = it
}
