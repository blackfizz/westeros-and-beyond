plugins {
    id(Plugins.libraryAndroid)
}

dependencies {
    implementation(project(Modules.model))
    implementation(project(Modules.repository))

    implementation(Dependencies.kotlinSerialization)

    implementation(Dependencies.roomRuntime)
    kapt(Dependencies.roomCompiler)
    implementation(Dependencies.roomKtx)
    implementation(Dependencies.roomPaging)

    testImplementation(Dependencies.roomTesting)
}