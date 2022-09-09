plugins {
    id(Plugins.kotlinKapt)
}

kapt {
    correctErrorTypes = true
}

task<Delete>("clean") {
    delete(rootProject.buildDir)
}