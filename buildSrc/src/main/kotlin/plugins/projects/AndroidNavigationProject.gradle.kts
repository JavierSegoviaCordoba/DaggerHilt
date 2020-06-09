import plugins.projects.baseBuildTypes
import plugins.projects.baseConfig

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("androidx.navigation.safeargs.kotlin")
}

aptRuntime2CompileClasspath()

android {
    baseConfig()
    baseBuildTypes()
}

dependencies {
    JvmLibs.apply {
        implementation(kotlinStdlib8)
    }

    AndroidLibs.apply {
        implementation(playCore)
        implementation(navigationDynamic)
        implementation(navigationFragment)
        implementation(navigationUI)
    }
}
