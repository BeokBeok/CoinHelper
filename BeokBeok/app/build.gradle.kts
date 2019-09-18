import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id(Plugins.ANDROID_APPLICATION)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.KOTLIN_KAPT)
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
    implementation(Libraries.Default.KOTLIN_STDLIB_JDK7)
    implementation(Libraries.Default.MATERIAL)
    implementation(Libraries.Default.CONSTRAINT_LAYOUT)

    androidTestImplementation(Libraries.Test.JUNIT)
    androidTestImplementation(Libraries.Test.ESPRESSO)
    testImplementation(Libraries.Test.KOTLIN)

    implementation(Libraries.Retrofit.MAIN)
    implementation(Libraries.Retrofit.CONVERT_GSON)
    implementation(Libraries.Retrofit.LOGGING_INTERCEPTOR)

    implementation(Libraries.RxJava.MAIN)
    implementation(Libraries.RxJava.RXANDROID)

    implementation(Libraries.LifeCycle.EXTENSIONS)
    implementation(Libraries.LifeCycle.VIEWMODEL)

    implementation(Libraries.Room.RUNTIME)
    implementation(Libraries.Room.KTX)
    kapt(Libraries.Room.COMPILER)

    implementation(Libraries.Koin.ANDROID)
    implementation(Libraries.Koin.VIEWMODEL)

    implementation(Libraries.Coroutine.CORE)
}
