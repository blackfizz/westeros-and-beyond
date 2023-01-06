plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    google()
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

// Dependencies which are defined in the buildSrc build.gradle.kts are effectively classpath
// dependencies, which are normally added in the project root build.gradle.kts
dependencies {
    implementation("com.android.tools.build:gradle:7.3.1")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.20")
    implementation("org.jetbrains.kotlin:kotlin-serialization:1.7.20")
    implementation("com.google.dagger:hilt-android-gradle-plugin:2.43.1")
    implementation(kotlin("script-runtime"))
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_11.toString()
    kotlinOptions.languageVersion = "1.7"
}