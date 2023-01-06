plugins {
    id(Plugins.libraryAndroid)
}

dependencies {
    implementation(project(Modules.model))
    implementation(project(Modules.domain))
}
android {
    namespace = "io.redandroid.westerosandbeyond.repository"
}
