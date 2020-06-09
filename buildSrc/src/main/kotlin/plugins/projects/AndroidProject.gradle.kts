import plugins.projects.baseConfig

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("dagger.hilt.android.plugin")
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
        implementation(converter)
        implementation(coroutines)
        implementation(kotlinStdlib8)
        implementation(dagger)
        implementation(okHttp)
        implementation(retrofit)
        implementation(retrofitMock)
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
