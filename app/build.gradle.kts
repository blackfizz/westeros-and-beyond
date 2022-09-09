plugins {
    id(Plugins.androidApplication)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinKapt)
    id(Plugins.dagger)
}

android {
    compileSdk = Config.compileSdk

    defaultConfig {
        applicationId = Config.applicationId
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk
        versionCode = Config.versionCode
        versionName = Config.versionName

        testInstrumentationRunner = Dependencies.junitRunner
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(Dependencies.daggerAndroid)
    kapt(Dependencies.daggerCompiler)

    implementation (Dependencies.coreKtx)
    implementation (Dependencies.lifecycleKtx)

    implementation (Dependencies.composeUi)
    implementation (Dependencies.composeMaterial)
    implementation (Dependencies.composeToolingPreview)
    implementation (Dependencies.composeActivity)
    androidTestImplementation (Dependencies.composeUiTestJunit)
    debugImplementation (Dependencies.composeUiTooling)
    debugImplementation (Dependencies.composeUiTestManifest)

    testImplementation (Dependencies.junit)
    androidTestImplementation (Dependencies.junitExt)
    androidTestImplementation (Dependencies.espresso)
}