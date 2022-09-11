plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinKapt)
}

android {
    compileSdk = Config.compileSdk

    defaultConfig {
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk

        testInstrumentationRunner = Dependencies.junitRunner
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
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
}

dependencies {
    implementation(Dependencies.timber)

    implementation(Dependencies.kotlinStdLib)
    implementation(Dependencies.kotlinCoroutinesCore)
    implementation(Dependencies.kotlinCoroutinesAndroid)
    implementation(Dependencies.kotlinCoroutinesTest)

    implementation(Dependencies.daggerAndroid)
    kapt(Dependencies.daggerCompiler)

    testImplementation (Dependencies.junit)
}
