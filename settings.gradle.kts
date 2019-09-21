pluginManagement {
    plugins {
        val nebulaInfoVersion: String by settings
        val nebulaReleaseVersion: String by settings
        id("nebula.info-scm") version nebulaInfoVersion
        id("nebula.release") version nebulaReleaseVersion
    }
}

rootProject.name = "cocozzelle"

file("$rootDir/modules").listFiles { f ->
    f.isDirectory && f.listFiles { bf ->
        bf.isFile && listOf("build.gradle", "build.gradle.kts").contains(bf.name) }?.isNotEmpty() ?: false
}?.forEach {
    include(":${it.name}")
    project(":${it.name}").projectDir = it
}
