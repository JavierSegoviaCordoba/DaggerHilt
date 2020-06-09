import com.android.build.gradle.AppExtension
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.api.BaseVariant
import com.android.build.gradle.internal.publishing.AndroidArtifacts.ARTIFACT_TYPE
import org.gradle.api.DomainObjectSet
import org.gradle.api.Project
import org.gradle.api.tasks.TaskDependency
import org.gradle.api.tasks.compile.AbstractCompile
import org.gradle.kotlin.dsl.the

fun Project.aptRuntime2CompileClasspath() = afterEvaluate {
    @Suppress("UNCHECKED_CAST")
    val variants: DomainObjectSet<BaseVariant> = when (val android = the<BaseExtension>()) {
        is AppExtension -> android.applicationVariants
        is LibraryExtension -> android.libraryVariants
        else -> error("Unrecognized android extension $android")
    } as DomainObjectSet<BaseVariant>

    for (variant in variants) {
        val compileJavaWithJavac = variant.javaCompileProvider
        val runtimeClasspath = variant.runtimeConfiguration
        val runtimeClasspathJars = runtimeClasspath.copyRecursive().apply {
            val attributeArtifactType = ARTIFACT_TYPE
            val runtimeClasspathArtifact = "android-classes"
            attributes.attribute(attributeArtifactType, runtimeClasspathArtifact)
        }
        val runtimeClasspathJarsTasks: TaskDependency = runtimeClasspathJars.buildDependencies
        val runtimeClasspathJarsFiles = runtimeClasspathJars.fileCollection { true }
        compileJavaWithJavac.configure {
            dependsOn(runtimeClasspathJarsTasks)
            doFirst { classpath = classpath.plus(runtimeClasspathJarsFiles) }
        }
        @Suppress("DefaultLocale")
        val variantKaptTaskName = "kapt${variant.name.capitalize()}Kotlin"
        if (variantKaptTaskName !in tasks.names) continue
        tasks.named(variantKaptTaskName).configure {
            dependsOn(runtimeClasspathJarsTasks)
            /** classpath supplement */
            val kaptTask = this
            val kotlinCompileTask: AbstractCompile = javaClass.getField("kotlinCompileTask").run {
                isAccessible = true
                get(kaptTask)
            } as AbstractCompile
            kaptTask.doFirst {
                kotlinCompileTask.classpath = kotlinCompileTask.classpath.plus(runtimeClasspathJarsFiles)
            }
        }
    }
}
