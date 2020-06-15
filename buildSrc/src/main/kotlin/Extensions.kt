import com.android.build.gradle.AppExtension
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.api.BaseVariant
import com.android.build.gradle.internal.publishing.AndroidArtifacts.ARTIFACT_TYPE
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.DomainObjectSet
import org.gradle.api.Project
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.tasks.TaskDependency
import org.gradle.api.tasks.compile.AbstractCompile
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.the

internal inline fun Project.android(block: BaseExtension.() -> Unit) {
    val baseExtension = this.extensions.getByType<BaseExtension>()
    return block(baseExtension)
}

internal fun Project.dependencies(configuration: DependencyHandlerScope.() -> Unit) =
    DependencyHandlerScope.of(dependencies).configuration()

internal fun DependencyHandler.implementation(dependencyNotation: Any): Dependency? =
    add("implementation", dependencyNotation)

internal fun DependencyHandler.api(dependencyNotation: Any): Dependency? = add("api", dependencyNotation)

internal fun DependencyHandler.testImplementation(dependencyNotation: Any): Dependency? =
    add("testImplementation", dependencyNotation)

internal fun DependencyHandler.androidTestImplementation(dependencyNotation: Any): Dependency? =
    add("androidTestImplementation", dependencyNotation)

internal fun DependencyHandler.kapt(dependencyNotation: Any): Dependency? = add("kapt", dependencyNotation)

fun Project.detekt(configure: DetektExtension.() -> Unit) = extensions.configure(DetektExtension::class.java, configure)

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
