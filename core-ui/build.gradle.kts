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
    namespace = "io.redandroid.westerosandbeyond.core_ui"
}

dependencies {
    implementation(project(Modules.core))

    api(Dependencies.composeUi)
    api(Dependencies.composeRuntimeLiveData)
    api(Dependencies.composeMaterial)
    api(Dependencies.composeToolingPreview)
    api(Dependencies.composeActivity)
    api(Dependencies.pagingCompose)

    debugImplementation (Dependencies.composeUiTooling)
}