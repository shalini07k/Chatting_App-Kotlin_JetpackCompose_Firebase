plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    kotlin("plugin.serialization") version "2.2.10"
    // it was supposed to be the plugin for ksp but because it didn't work kapt of Kotlin was used
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    alias(libs.plugins.google.gms.google.services)

}

android {
    namespace = "com.example.chatting_app"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.chatting_app"
        minSdk = 28
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    //implementation(libs.firebase.database)
    //implementation(libs.firebase.auth.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //jetpackcompose navigation
    implementation("androidx.navigation:navigation-compose:2.9.3")
    //serialization- the original version was unstable so I changed it from 1.9.0 to 1.6.3.
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")

    //HILT (Dependencies Injection(DI))

    // the installation of hilt (DI)
    implementation("com.google.dagger:hilt-android:2.56.2")
    //here ksp was reframed to kapt because of its unstable nature
    kapt("com.google.dagger:hilt-android-compiler:2.56.2")

    //the dependency for hilt to be integrated with other jetpack compose libraries like navigation
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
    implementation(platform("com.google.firebase:firebase-bom:34.1.0"))
    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.firebase:firebase-database")
    implementation("com.google.firebase:firebase-firestore")

    //coil(help in utilizing online images(https))
    implementation("io.coil-kt:coil-compose:2.7.0")


}