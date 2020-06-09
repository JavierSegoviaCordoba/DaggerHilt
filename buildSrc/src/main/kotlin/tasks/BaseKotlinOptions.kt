package tasks

import org.gradle.api.JavaVersion
import org.gradle.kotlin.dsl.TaskContainerScope
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val TaskContainerScope.baseKotlinOptions: Unit
    get() {
        withType<KotlinCompile>().all {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_1_8.toString()
                freeCompilerArgs = freeCompilerArgs + listOf(
                    "-Xinline-classes",
                    "-Xopt-in=kotlin.ExperimentalStdlibApi",
                    "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                    "-Xopt-in=kotlinx.coroutines.ObsoleteCoroutinesApi",
                    "-Xopt-in=kotlinx.coroutines.FlowPreview",
                    "-Xopt-in=kotlinx.serialization.ImplicitReflectionSerializer",
                    "-Xopt-in=kotlinx.serialization.UnstableDefault",
                    "-Xopt-in=kotlin.RequiresOptIn"
                )
            }
        }
    }
