pluginManagement {
    plugins {
        val nebulaInfoPluginVersion: String by settings
        val nebulaReleasePluginVersion: String by settings
        id("nebula.info-scm") version nebulaInfoPluginVersion
        id("nebula.release") version nebulaReleasePluginVersion
    }
}

rootProject.name = "kokozzelle"

file("$rootDir/modules").listFiles { f ->
    f.isDirectory && f.listFiles { bf ->
        bf.isFile && listOf("build.gradle", "build.gradle.kts").contains(bf.name) }?.isNotEmpty() ?: false
}?.forEach {
    include(":${rootProject.name}-${it.name}")
    project(":${rootProject.name}-${it.name}").projectDir = it
}
