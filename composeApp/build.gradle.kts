import org.jetbrains.compose.ComposeExtension
import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.kover) // Pour le coverage des tests
}

kotlin {
    apply(plugin = "com.google.devtools.ksp")

    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "11"
            }
        }
    }

    jvm("desktop")

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
            binaryOption(
                "bundleId",
                "fr.togethim.mtg_life.shared"
            ) // Pour éviter l'erreur : Cannot create binary debugFramework: binary with such a name already exists
        }
    }

    sourceSets {
        val desktopMain by getting

        androidMain.dependencies {
            implementation(libs.compose.ui.tooling.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.compose.ui)
            implementation(libs.ktor.client.okhttp)

            // Pour les previews Android
            api(compose.preview)
            api(compose.uiTooling)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            @OptIn(ExperimentalComposeLibrary::class)
            implementation(compose.components.resources)
            implementation(projects.shared)
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.json)
            implementation(libs.ktor.client.serialization)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.ktor.client.cio)
            implementation(libs.ktor.client.websocket)
            implementation(libs.koin.annotations)
            implementation(libs.moko.mvvm.core)
            implementation(libs.moko.mvvm.compose)
            implementation(libs.koin.core)
            implementation(libs.decompose)
            implementation(libs.decompose.dekstop)
            implementation(libs.multiplatform.settings)
            implementation(libs.splashScreen)
            implementation(libs.kottie)
        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutines.swing)
            implementation(libs.ktor.client.okhttp)
        }
        commonTest {
            dependencies {
                implementation(libs.koin.test)
                implementation(libs.kotlinx.coroutines.test)
                implementation(libs.mockative)
            }
        }
    }

    dependencies {
        add("kspCommonMainMetadata", libs.koin.compiler)
        configurations
            .filter { it.name.startsWith("ksp") && it.name.contains("Test") }
            .forEach {
                add(it.name, libs.mockative.processor)
            }
    }
}
android {
    namespace = "fr.togethim.mtg_life"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "fr.togethim.mtg_life"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 3
        versionName = "1.0.0"
    }

    // Pour les previews sur Android : https://github.com/JetBrains/compose-multiplatform/issues/3499#issuecomment-1904675675
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = extensions.getByType(ComposeExtension::class.java)
            .dependencies.compiler.auto.substringAfterLast(":")
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    dependencies {
        debugImplementation(compose.uiTooling)
        implementation(libs.compose.ui.tooling.preview)
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "fr.togethim.mtg_life"
            packageVersion = "1.0.0"
        }
    }
}

// Pour ne pas avoir de coverage sur les classes injectées
koverReport {
    defaults {
        filters {
            excludes {
                classes("*inject*")
                classes("*Composable*")
                classes("*SettingConfig")
                classes("AppModuleKt")
                classes("SettingsConfigKt")
                classes("MainKt")
                classes("AppKt*")
                classes("commun.theme.*")
                classes("*RootNavigationComponent*")
                classes("di.*")
                classes("specificitePlateforme.*")
                classes("*ResultatAppels*")
                classes("*Api*")
                classes("*JVMPlatform*")
            }
        }
    }
}