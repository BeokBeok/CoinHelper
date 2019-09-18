object Libraries {

    object Default {
        private const val KOTLIN_VERSION = "1.3.50"
        private const val MATERIAL_VERSION = "1.0.0"
        private const val CONSTRAINT_LAYOUT_VERSION = "1.1.3"

        const val KOTLIN_STDLIB_JDK7 = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$KOTLIN_VERSION"
        const val MATERIAL = "com.google.android.material:material:$MATERIAL_VERSION"
        const val CONSTRAINT_LAYOUT =
            "androidx.constraintlayout:constraintlayout:$CONSTRAINT_LAYOUT_VERSION"
    }

    object Test {
        private const val JUNIT_VERSION = "1.1.1"
        private const val ESPRESSO_VERSION = "3.2.0"
        private const val KOTLIN_JUNIT_VERSION = "1.3.50"

        const val JUNIT = "androidx.test.ext:junit:$JUNIT_VERSION"
        const val ESPRESSO = "androidx.test.espresso:espresso-core:$ESPRESSO_VERSION"
        const val KOTLIN = "org.jetbrains.kotlin:kotlin-test-junit:$KOTLIN_JUNIT_VERSION"
    }

    object Retrofit {
        private const val VERSION = "2.6.1"
        private const val LOGGING_INTERCEPTOR_VERSION = "4.1.0"

        const val MAIN = "com.squareup.retrofit2:retrofit:$VERSION"
        const val CONVERT_GSON = "com.squareup.retrofit2:converter-gson:$VERSION"
        const val LOGGING_INTERCEPTOR =
            "com.squareup.okhttp3:logging-interceptor:$LOGGING_INTERCEPTOR_VERSION"
    }

    object RxJava {
        private const val VERSION = "2.2.11"
        private const val ANDROID_VERSION = "2.1.1"

        const val MAIN = "io.reactivex.rxjava2:rxjava:$VERSION"
        const val RXANDROID = "io.reactivex.rxjava2:rxandroid:$ANDROID_VERSION"
    }

    object LifeCycle {
        private const val VERSION = "2.1.0"

        const val EXTENSIONS = "androidx.lifecycle:lifecycle-extensions:$VERSION"
        const val VIEWMODEL = "androidx.lifecycle:lifecycle-viewmodel-ktx:$VERSION"
    }

    object Room {
        private const val VERSION = "2.1.0"

        const val RUNTIME = "androidx.room:room-runtime:$VERSION"
        const val KTX = "androidx.room:room-ktx:$VERSION"
        const val COMPILER = "androidx.room:room-compiler:$VERSION"
    }

    object Koin {
        private const val VERSION = "2.0.1"

        const val ANDROID = "org.koin:koin-android:$VERSION"
        const val VIEWMODEL = "org.koin:koin-android-viewmodel:$VERSION"
    }

    object Coroutine {
        private const val VERSION = "1.2.1"

        const val CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$VERSION"
    }
}