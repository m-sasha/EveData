plugins {
    kotlin("jvm") version "2.0.20"
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.20"
    id("org.jetbrains.compose") version "1.6.11"
    id("de.undercouch.download") version "5.6.0"  // Download task
}

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

dependencies {
    implementation(compose.runtime)
}

kotlin {
    jvmToolchain(17)

    compilerOptions.freeCompilerArgs.apply {
        add("-Xlambdas=indy")
        add("-Xcontext-receivers")
    }
}

// Converts the SDE into an Eve data file the app can read.
open class ImportEveDataTask : DefaultTask() {

    @InputFile
    lateinit var sdeFile: File

    @InputDirectory
    lateinit var eveCacheDir: File

    @InputFile
    lateinit var output: File

    @InputDirectory
    lateinit var iconsOutputDir: File

    @TaskAction
    fun importData() {
        importSde(
            sdeFile = sdeFile,
            eveCacheDir = eveCacheDir,
            outputFile = output,
            iconsOutputDir = iconsOutputDir
        )
    }

}

// Generate the eve data file from the SDE:
// https://wiki.eveuniversity.org/Static_Data_Export
// https://developers.eveonline.com/resource/resources
val sdeFile = File(projectDir, "sde.zip").normalize()

// Generate the eve data
val generateEveData = tasks.register<ImportEveDataTask>("generateEveData") {
    group = "eve"
    sdeFile = this@Build_gradle.sdeFile
    eveCacheDir = File(System.getProperty("user.home"), "Library/Application Support/EVE Online/SharedCache").normalize()
    output = project.file("src/main/resources/eve_data.dat")
    iconsOutputDir = project.file("src/main/resources/icons")

    mustRunAfter(downloadSdeFile)
}


// Download the SDE file
val downloadSdeFile = tasks.register<de.undercouch.gradle.tasks.download.Download>("downloadSdeFile") {
    src("https://eve-static-data-export.s3-eu-west-1.amazonaws.com/tranquility/sde.zip")
    dest(sdeFile)
    connectTimeout(10_000)
    readTimeout(10_000)
}

// Re-download the SDE file and re-generate the eve data
tasks.register("updateEveData") {
    group = "eve"
    dependsOn(downloadSdeFile, generateEveData)
}

kotlin {
    jvmToolchain(17)
}
