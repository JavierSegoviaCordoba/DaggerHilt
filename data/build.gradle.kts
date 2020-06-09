plugins {
    AndroidProject
}

dependencies {
    Libs.apply {
        implementation(networkResponse)
    }

    JvmLibs.apply {
        implementation(converter)
        implementation(coroutines)
    }
}
