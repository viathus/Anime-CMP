@file:Suppress("DEPRECATION")

import com.codingfeline.buildkonfig.compiler.FieldSpec
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.util.regex.Pattern

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.ksp)
    alias(libs.plugins.room)
    alias(libs.plugins.kotlinxSerialization)
    alias(libs.plugins.buildkonig)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    sourceSets.commonMain {
        kotlin.srcDir("build/generated/ksp/metadata")
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    sourceSets {
//        all {
//            languageSettings.enableLanguageFeature("ExplicitBackingFields")
//        }

        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.ktor.client.okhttp)
            implementation(libs.koin.android)
            implementation(libs.koin.androidx.compose)
            implementation(libs.media3.exoplayer)
            implementation(libs.androidx.media3.exoplayer.dash)
            implementation(libs.androidx.media3.ui)
            implementation(libs.youtube.player)
            implementation(libs.androidx.webkit)
            implementation(libs.core.splashscreen)
        }

        commonMain.dependencies {
            // compose
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.animation)
            implementation(compose.material)
            implementation(compose.material3)

            implementation(compose.ui)
            implementation(libs.compose.navigation)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)

            // di
            api(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)

            // standard
            implementation(libs.lifecycle.viewmodel)
            implementation(libs.coroutines.core)

            // network
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.client.encoding)
            implementation(libs.ktor.client.logging)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.kotlinx.serialization)

            // db
            implementation(libs.room.runtime)
            implementation(libs.sqlite.bundled)

            // image loading
            implementation(libs.coil.compose.core)
            implementation(libs.coil.compose)
            implementation(libs.coil.svg)
            implementation(libs.coil.mp)
            implementation(libs.coil.network.ktor)
            implementation(libs.kmpalette.core)

            // logging
            implementation(libs.napier)
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
    }
}

android {
    namespace = "com.anime.cmp"
    compileSdk =
        libs.versions.android.compileSdk
            .get()
            .toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "com.anime.cmp"
        minSdk =
            libs.versions.android.minSdk
                .get()
                .toInt()
        targetSdk =
            libs.versions.android.targetSdk
                .get()
                .toInt()
        versionCode = 1
        versionName = "1.0"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }

        getByName("debug") {
            isMinifyEnabled = false
            isDebuggable = true
        }

        create("debuggableRelease") {
            isMinifyEnabled = true
            isDebuggable = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
            signingConfig = signingConfigs.getByName("debug")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
    dependencies {
        debugImplementation(compose.uiTooling)
    }

    flavorDimensions.add("variant")

    productFlavors {
        create("dev") {
            dimension = "variant"
            isDefault = true
            applicationIdSuffix = ".dev"
        }

        create("prod") {
            dimension = "variant"
        }
    }
}

dependencies {
    add("kspCommonMainMetadata", libs.room.compiler)
}

room {
    schemaDirectory("$projectDir/schemas")
}

tasks.withType<org.jetbrains.kotlin.gradle.dsl.KotlinCompile<*>>().configureEach {
    if (name != "kspCommonMainKotlinMetadata") {
        dependsOn("kspCommonMainKotlinMetadata")
    }
}

// sets the current environment to ios variant or android variant
project.extra.set("buildkonfig.flavor", currentBuildVariant())

// configures the different variants we have
buildkonfig {
    packageName = "com.anime.cmp"
    objectName = "AppConfig"
    exposeObjectWithName = "AppConfig"

    defaultConfigs {
        buildConfigField(FieldSpec.Type.STRING, "variant", "dev")
        buildConfigField(FieldSpec.Type.BOOLEAN, "DEBUG", "true")
        buildConfigField(FieldSpec.Type.STRING, "apiEndPoint", "https://dev.example.com")
    }

    defaultConfigs("dev") {
        buildConfigField(FieldSpec.Type.STRING, "variant", "dev")
        buildConfigField(FieldSpec.Type.BOOLEAN, "DEBUG", "true")
        buildConfigField(FieldSpec.Type.STRING, "apiEndPoint", "https://dev.example.com")
    }

    defaultConfigs("prod") {
        buildConfigField(FieldSpec.Type.STRING, "variant", "prod")
        buildConfigField(FieldSpec.Type.BOOLEAN, "DEBUG", "false")
        buildConfigField(FieldSpec.Type.STRING, "apiEndPoint", "https://example.com")
    }
}

//
fun Project.getAndroidBuildVariantOrNull(): String? {
    val variants = setOf("dev", "prod")
    val taskRequestsStr = gradle.startParameter.taskRequests.toString()

    val pattern: Pattern =
        if (taskRequestsStr.contains("assemble")) {
            Pattern.compile("assemble(\\w+)(Release|Debug)")
        } else {
            Pattern.compile("bundle(\\w+)(Release|Debug)")
        }

    val matcher = pattern.matcher(taskRequestsStr)
    val variant = if (matcher.find()) matcher.group(1).lowercase() else null
    return if (variant in variants) {
        variant
    } else {
        null
    }
}

// grab either the android variant, if android app or ios variant if ios
private fun Project.currentBuildVariant(): String {
    val variants = setOf("dev", "prod")

    return getAndroidBuildVariantOrNull() ?: System.getenv()["VARIANT"].toString()
        .takeIf { it in variants } ?: "dev"
}
