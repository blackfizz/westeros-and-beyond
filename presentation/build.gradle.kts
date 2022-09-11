plugins {
    id(Plugins.libraryAndroid)
}

android {
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }
}

dependencies {
    implementation(project(Modules.model))
    implementation(project(Modules.domain))
    implementation(project(Modules.coreUi))

    implementation(Dependencies.lifecycleKtx)
    implementation(Dependencies.lifecycleLiveData)
    implementation(Dependencies.lifecycleViewModel)
    implementation(Dependencies.composeHiltNavigation)
}