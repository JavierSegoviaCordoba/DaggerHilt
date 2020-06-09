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
}
