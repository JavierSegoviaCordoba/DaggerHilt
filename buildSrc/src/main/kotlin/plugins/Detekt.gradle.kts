plugins {
    id("io.gitlab.arturbosch.detekt")
}

dependencies {
    detektPlugins(JvmLibs.detektFormatting)
}

detekt {
    ignoreFailures = true
    autoCorrect = true

    reports {
        html {
            enabled = true
            destination = file("$buildDir/reports/detekt/detekt.html")
        }
        txt.enabled = false
        xml.enabled = false
    }
}
