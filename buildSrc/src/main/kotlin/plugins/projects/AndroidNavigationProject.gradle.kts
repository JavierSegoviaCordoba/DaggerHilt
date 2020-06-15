import plugins.projects.baseBuildTypes
import plugins.projects.baseConfig

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("dagger.hilt.android.plugin")
}

aptRuntime2CompileClasspath()

android {
    baseConfig()
    baseBuildTypes()
}

dependencies {
    JvmLibs.apply {
        implementation(dagger)
        implementation(kotlinStdlib8)
    }

    JvmKaptLibs.apply {
        kapt(daggerCompiler)
    }

    AndroidLibs.apply {
        implementation(daggerHilt)
        implementation(playCore)
        implementation(navigationDynamic)
        implementation(navigationFragment)
        implementation(navigationUI)
    }

    AndroidKaptLibs.apply {
        kapt(daggerHiltAndroidxCompiler)
        kapt(daggerHiltCompiler)
    }
}
