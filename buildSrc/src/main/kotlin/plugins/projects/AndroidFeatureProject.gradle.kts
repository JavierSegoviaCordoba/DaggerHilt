import plugins.projects.baseBuildTypes
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
    baseBuildTypes()
}

dependencies {
    Projects.apply {
        implementation(project(di))
        implementation(project(domain))
    }

    Projects.Presentation.apply {
        implementation(project(navigation))
    }

    Libs.apply {
        implementation(core)
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
        implementation(playCore)
        implementation(activity)
        implementation(appCompat)
        implementation(constraintLayout)
        implementation(daggerHilt)
        implementation(daggerHiltLifecycleViewModel)
        implementation(fragment)
        implementation(material)
        implementation(recyclerView)
        implementation(swipeRefreshLayout)
    }

    AndroidKaptLibs.apply {
        kapt(daggerHiltAndroidxCompiler)
        kapt(daggerHiltCompiler)
    }
}
