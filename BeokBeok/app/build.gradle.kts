import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(29)
    defaultConfig {
        applicationId = "com.architecturestudy"
        minSdkVersion(23)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    dataBinding {
        isEnabled = true
    }
    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }
}

dependencies {
    // Default
    implementation(
        fileTree(
            mapOf(
                "dir" to "libs",
                "include" to listOf("*.jar")
            )
        )
    )
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.3.50")

    // AndroidX
    implementation("com.google.android.material:material:1.0.0")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")

    // Test Library
    androidTestImplementation("androidx.test.ext:junit:1.1.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:1.3.50")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.6.1")
    implementation("com.squareup.retrofit2:converter-gson:2.6.1")
    implementation("com.squareup.okhttp3:logging-interceptor:4.1.0")

    // Rxjava
    implementation("io.reactivex.rxjava2:rxjava:2.2.11")
    implementation("io.reactivex.rxjava2:rxandroid:2.1.1")

    // LifeCycle
    implementation("androidx.lifecycle:lifecycle-extensions:2.1.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.1.0")

    // Room
    implementation("androidx.room:room-runtime:2.1.0")
    implementation("androidx.room:room-ktx:2.1.0")
    kapt("androidx.room:room-compiler:2.1.0")

    // Koin
    implementation("org.koin:koin-android:2.0.1")
    implementation("org.koin:koin-android-viewmodel:2.0.1")

    // Coroutine
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.2.1")
}
