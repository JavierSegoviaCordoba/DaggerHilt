plugins {
    `kotlin-dsl`
}

val androidGradle = "4.1.0-alpha10"
val safeArgs = "2.3.0-beta01"
val kotlin = "1.4-M2"
val dependencyUpdates = "0.28.0"
val detekt = "1.9.1"
val daggerHilt = "2.28-alpha"

repositories {
    maven("https://oss.sonatype.org/content/repositories/snapshots/")
    maven("https://androidx.dev/snapshots/builds/6564799/artifacts/repository")
    maven("https://dl.bintray.com/kotlin/kotlin-eap")
    maven("https://plugins.gradle.org/m2")
    jcenter()
    mavenCentral()
    google()
}

dependencies {
    implementation(gradleApi())
    implementation(localGroovy())
    implementation("com.android.tools.build:gradle:$androidGradle")
    implementation("androidx.navigation:navigation-safe-args-gradle-plugin:$safeArgs")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin")
    implementation("org.jetbrains.kotlin:kotlin-serialization:$kotlin")
    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:$detekt")
    implementation("com.github.ben-manes:gradle-versions-plugin:$dependencyUpdates")
    implementation("com.google.dagger:hilt-android-gradle-plugin:$daggerHilt")
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}
