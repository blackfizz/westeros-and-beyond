plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {

    implementation(Dependencies.kotlinStdLib)
    implementation(Dependencies.kotlinCoroutinesCore)

    testImplementation(Dependencies.junit)
    testImplementation(Dependencies.kotlinCoroutinesTest)
    testImplementation(Dependencies.mockk)
    testImplementation(Dependencies.coreRules)
    testImplementation(Dependencies.archCoreTesting)
}