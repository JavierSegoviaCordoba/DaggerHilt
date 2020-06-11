import com.android.build.gradle.AppExtension
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.api.BaseVariant
import com.android.build.gradle.internal.dsl.BuildType
import com.android.build.gradle.internal.publishing.AndroidArtifacts.ARTIFACT_TYPE
import org.gradle.api.DomainObjectSet
import org.gradle.api.NamedDomainObjectContainer
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

fun DependencyHandler.implementationProject(dependencyNotation: Any): Dependency? =
    implementation(project(mapOf("path" to dependencyNotation)))

fun DependencyHandler.implementationProjectDefault(dependencyNotation: Any): Dependency? =
    implementation(project(mapOf("path" to dependencyNotation, "configuration" to "default")))

fun DependencyHandler.apiProject(dependencyNotation: Any): Dependency? =
    api(project(mapOf("path" to dependencyNotation)))

internal fun DependencyHandler.implementation(dependencyNotation: Any): Dependency? =
    add("implementation", dependencyNotation)

internal fun DependencyHandler.api(dependencyNotation: Any): Dependency? =
    add("api", dependencyNotation)

internal fun DependencyHandler.testImplementation(dependencyNotation: Any): Dependency? =
    add("testImplementation", dependencyNotation)

internal fun DependencyHandler.androidTestImplementation(dependencyNotation: Any): Dependency? =
    add("androidTestImplementation", dependencyNotation)

internal fun DependencyHandler.kapt(dependencyNotation: Any): Dependency? =
    add("kapt", dependencyNotation)

internal val Project.isAndroidApplication get() = findInGradleFile("Plugins.androidApplication")
internal val Project.isAndroidLibrary get() = findInGradleFile("Plugins.androidLibrary")
internal val Project.isAndroidDynamicFeature get() = findInGradleFile("Plugins.androidDynamicFeature")

inline fun <reified T : BuildType> NamedDomainObjectContainer<T>.release(
    crossinline buildType: BuildType.() -> Unit
): T = this.getByName("release") { buildType(this) }

internal inline fun <reified T : BuildType> NamedDomainObjectContainer<T>.debug(
    crossinline buildType: BuildType.() -> Unit
): T = this.getByName("debug") { buildType(this) }

internal inline fun <reified T : BuildType> NamedDomainObjectContainer<T>.pro(
    crossinline buildType: BuildType.() -> Unit
): T = this.create("pro") { buildType(this).apply { initWith(getByName("release")) } }

internal inline fun <reified T : BuildType> NamedDomainObjectContainer<T>.pre(
    crossinline buildType: BuildType.() -> Unit
): T = this.create("pre") { buildType(this).apply { initWith(getByName("debug")) } }

internal inline fun <reified T : BuildType> NamedDomainObjectContainer<T>.beta(
    crossinline buildType: BuildType.() -> Unit
): T = this.create("beta") { buildType(this).apply { initWith(getByName("release")) } }

internal inline fun <reified T : BuildType> NamedDomainObjectContainer<T>.alpha(
    crossinline buildType: BuildType.() -> Unit
): T = this.create("alpha") { buildType(this).apply { initWith(getByName("release")) } }

internal inline fun <reified T : BuildType> NamedDomainObjectContainer<T>.dev(
    crossinline buildType: BuildType.() -> Unit
): T = this.create("dev") { buildType(this).apply { initWith(getByName("debug")) } }

internal inline fun <reified T : BuildType> NamedDomainObjectContainer<T>.internal(
    crossinline buildType: BuildType.() -> Unit
): T = this.create("internal") { buildType(this).apply { initWith(getByName("debug")) } }

internal inline fun <reified T : BuildType> NamedDomainObjectContainer<T>.mock(
    crossinline buildType: BuildType.() -> Unit
): T = this.create("mock") { buildType(this).apply { initWith(getByName("debug")) } }

private fun Project.findInGradleFile(fileName: String): Boolean {
    project.rootDir.walkTopDown()
        .first { file -> file.name.contains("build.gradle") }
        .readText().apply { return contains(fileName) }
}

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
