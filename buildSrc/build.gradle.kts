plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    maven("https://oss.sonatype.org/content/groups/public/")  // For SnakeYAML
}


dependencies {
    implementation("org.yaml:snakeyaml:2.0")
}


kotlin {
    jvmToolchain(17)
}
