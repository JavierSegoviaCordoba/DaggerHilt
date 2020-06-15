package plugins.projects

import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion

private object SdkVersion {
    const val min = 21
    const val targetAndCompile = 29
}

fun BaseExtension.baseConfig() {
    defaultConfig {
        minSdkVersion(SdkVersion.min)
        targetSdkVersion(SdkVersion.targetAndCompile)
        compileSdkVersion(SdkVersion.targetAndCompile)
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    @Suppress("UnstableApiUsage")
    buildFeatures.viewBinding = true

    packagingOptions {
        setExcludes(
            setOf(
                "META-INF/LICENSE.md",
                "META-INF/LICENSE-notice.md",
                "META-INF/MANIFEST.MF",
                "META-INF/maven/com.google.code.findbugs/jsr305/pom.xml",
                "META-INF/maven/com.google.code.findbugs/jsr305/pom.properties",
                "META-INF/proguard/coroutines.pro",
                "META-INF/*.kotlin_module"
            )
        )
    }

    lintOptions.isAbortOnError = false
}

fun BaseExtension.baseBuildTypes() {
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        getByName("debug") {
            isMinifyEnabled = false
        }

        create("mock") {
            initWith(getByName("debug"))
            matchingFallbacks = (matchingFallbacks + mutableListOf("debug")).toMutableList()
        }
    }
}
