plugins {
    id("org.jetbrains.kotlin.jvm")
    id("kotlin-kapt")
    id("org.jetbrains.kotlin.plugin.serialization")
}

dependencies {
    Libs.apply {
        implementation(resource)
    }

    JvmLibs.apply {
        implementation(coroutines)
        implementation(javaxInject)
        implementation(kotlinStdlib8)
        implementation(serialization)
    }
}
