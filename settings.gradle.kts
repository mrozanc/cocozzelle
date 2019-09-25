pluginManagement {
    plugins {
        val nebulaInfoPluginVersion: String by settings
        val nebulaReleasePluginVersion: String by settings
        id("nebula.info-scm") version nebulaInfoPluginVersion
        id("nebula.release") version nebulaReleasePluginVersion
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
