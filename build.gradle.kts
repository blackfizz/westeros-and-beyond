buildscript {

    dependencies {
        classpath(Dependencies.daggerGradlePlugin)
    }
}// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id(Plugins.androidApplication) version Versions.android apply false
    id(Plugins.androidLibrary) version Versions.android apply false
    id(Plugins.kotlinAndroid) version Versions.kotlin apply false
}

task<Delete>("clean") {
    delete(rootProject.buildDir)
}