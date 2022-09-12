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
    api(Dependencies.composeRuntimeLiveData)
    api(Dependencies.composeMaterial)
    api(Dependencies.composeToolingPreview)
    api(Dependencies.composeActivity)
    api(Dependencies.pagingCompose)
    androidTestApi (Dependencies.composeUiTestJunit)
    debugApi (Dependencies.composeUiTooling)
    debugApi (Dependencies.composeUiTestManifest)
}