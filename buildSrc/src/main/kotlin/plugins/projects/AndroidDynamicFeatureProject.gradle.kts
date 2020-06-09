import plugins.projects.baseBuildTypesDynamic
import plugins.projects.baseConfig

plugins {
    id("com.android.library")
//    id("com.android.dynamic-feature")
    id("kotlin-android")
    id("kotlin-kapt")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("dagger.hilt.android.plugin")
}

aptRuntime2CompileClasspath()

android {
    baseConfig()
    baseBuildTypesDynamic()
}

dependencies {
    Projects.apply {
        implementation(project(di))
        implementation(project(domain))
    }

    Projects.Presentation.apply {
//        implementation(project(app))
        implementation(project(navigation))
    }

    Libs.apply {
        implementation(core)
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
