plugins {
    AndroidProject
}

dependencies {
    Libs.apply {
        implementation(networkResponse)
    }

    JvmLibs.apply {
        implementation(converter)
        implementation(okHttp)
        implementation(retrofit)
        implementation(retrofitMock)
    }

    JvmTestLibs.apply {
        testImplementation(kotestJunit)
        testImplementation(kotestAssertions)
        implementation(mockWebServer)
    }

    AndroidLibs.apply {
        implementation(roomRuntime)
        implementation(roomKTX)
    }

    AndroidKaptLibs.apply {
        kapt(roomCompiler)
    }
}
