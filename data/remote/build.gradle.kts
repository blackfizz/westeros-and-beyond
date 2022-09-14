plugins {
    id(Plugins.libraryAndroid)
}

dependencies {
    implementation(project(Modules.model))
    implementation(project(Modules.repository))

    implementation(Dependencies.retrofit)
    implementation(Dependencies.retrofitCoroutinesAdapter)
    implementation(Dependencies.retrofitMoshi)
    implementation(Dependencies.okHttpLogger)

    implementation(Dependencies.moshi)
    kapt(Dependencies.moshiCodegen)
}