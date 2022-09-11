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

    implementation(project(Modules.core))
    implementation(project(Modules.coreUi))
    implementation(project(Modules.model))
    implementation(project(Modules.domain))
    implementation(project(Modules.repository))
    implementation(project(Modules.remote))
    implementation(project(Modules.local))
    implementation(project(Modules.presentation))

    implementation(Dependencies.timber)

    implementation(Dependencies.kotlinStdLib)
    implementation(Dependencies.kotlinCoroutinesCore)
    implementation(Dependencies.kotlinCoroutinesAndroid)

    implementation(Dependencies.daggerAndroid)
    kapt(Dependencies.daggerCompiler)

    implementation (Dependencies.coreKtx)
    implementation (Dependencies.lifecycleKtx)

    testImplementation (Dependencies.junit)
    androidTestImplementation (Dependencies.junitExt)
    androidTestImplementation (Dependencies.espresso)
    testImplementation(Dependencies.kotlinCoroutinesTest)
}