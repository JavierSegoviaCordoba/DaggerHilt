import plugins.projects.baseConfig

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
    id("org.jetbrains.kotlin.plugin.serialization")
}

aptRuntime2CompileClasspath()

android {
    baseConfig()
}

dependencies {
    Projects.apply {
        implementation(project(domain))
    }

    Libs.apply {
        implementation(resource)
    }

    JvmLibs.apply {
        implementation(coroutines)
        implementation(kotlinStdlib8)
        implementation(dagger)
        implementation(serialization)
    }

    JvmKaptLibs.apply {
        kapt(daggerCompiler)
    }

    AndroidLibs.apply {
        implementation(daggerHilt)
        implementation(daggerHiltLifecycleViewModel)
    }

    AndroidKaptLibs.apply {
        kapt(daggerHiltAndroidxCompiler)
        kapt(daggerHiltCompiler)
    }
}
