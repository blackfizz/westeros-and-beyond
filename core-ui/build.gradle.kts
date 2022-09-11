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
    implementation(project(Modules.core))

    api(Dependencies.composeUi)
    api(Dependencies.composeMaterial)
    api(Dependencies.composeToolingPreview)
    api(Dependencies.composeActivity)
    androidTestApi (Dependencies.composeUiTestJunit)
    debugApi (Dependencies.composeUiTooling)
    debugApi (Dependencies.composeUiTestManifest)
}