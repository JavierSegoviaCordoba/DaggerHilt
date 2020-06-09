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
        exclude("META-INF/*.kotlin_module")
    }
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
    }
}
