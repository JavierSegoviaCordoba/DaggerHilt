plugins {
    AndroidProject
}

dependencies {
    Projects.apply {
        implementation(project(data))
    }

    Libs.apply {
        implementation(networkResponse)
    }

    JvmLibs.apply {
        implementation(converter)
        implementation(okHttp)
        implementation(retrofit)
    }

    AndroidLibs.apply {
        implementation(roomRuntime)
        implementation(roomKTX)
    }

    AndroidKaptLibs.apply {
        kapt(roomCompiler)
    }
}
