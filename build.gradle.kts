import tasks.baseKotlinOptions

plugins {
    DependencyUpdates
    Detekt
}

allprojects {
    repositories {
        maven("https://oss.sonatype.org/content/repositories/snapshots/")
        maven("https://androidx.dev/snapshots/builds/6564799/artifacts/repository")
        maven("https://dl.bintray.com/kotlin/kotlin-eap")
        maven("https://plugins.gradle.org/m2")
        jcenter()
        mavenCentral()
        google()
    }

    tasks {
        withType<Delete> { delete(buildDir) }
        baseKotlinOptions
    }
}

tasks {
    withType<Test> {
        maxParallelForks = Runtime.getRuntime().availableProcessors()
        useJUnitPlatform()
        useTestNG()
    }
}
