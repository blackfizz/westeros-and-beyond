plugins {
    id(Plugins.libraryAndroid)
}

dependencies {
    implementation(project(Modules.model))
}
android {
    namespace = "io.redandroid.westerosandbeyond.domain"
}
