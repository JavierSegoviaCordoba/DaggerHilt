import plugins.projects.baseBuildTypes
import plugins.projects.baseConfig

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("dagger.hilt.android.plugin")
}

aptRuntime2CompileClasspath()

android {
    baseConfig()
    baseBuildTypes()
    defaultConfig.applicationId = "com.javiersc.daggerHilt"
}

dependencies {
    Projects.apply {
        implementation(project(data))
        implementation(project(domain))
    }

    Projects.Presentation.apply {
        implementation(project(common))
        implementation(project(navigation))
    }

    Projects.Presentation.Features.apply {
        implementation(project(main))
        implementation(project(champions))
        implementation(project(championDetail))
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
        api(playCore)
        implementation(activity)
        implementation(appCompat)
        implementation(constraintLayout)
        implementation(daggerHilt)
        implementation(daggerHiltLifecycleViewModel)
        implementation(fragment)
        implementation(material)
    }

    AndroidKaptLibs.apply {
        kapt(daggerHiltAndroidxCompiler)
        kapt(daggerHiltCompiler)
    }
}
