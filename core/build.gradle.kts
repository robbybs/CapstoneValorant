plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("kotlin-android")
    id("kotlin-parcelize")
}

android {
    namespace = "com.rbs.core"
    compileSdk = 33

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    val roomVersion = "2.5.2"
    val retrofitVersion = "2.9.0"
    val coroutinesVersion = "1.6.4"
    val koinVersion = "3.3.2"

    //add new
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    api("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.core:core-ktx:1.9.0")
    api("androidx.room:room-runtime:$roomVersion")
    api("androidx.room:room-ktx:$roomVersion")
    ksp("androidx.room:room-compiler:$roomVersion")
    androidTestImplementation("androidx.room:room-testing:$roomVersion")

    api("com.squareup.retrofit2:retrofit:$retrofitVersion")
    api("com.squareup.retrofit2:converter-gson:$retrofitVersion")
    api("com.squareup.okhttp3:logging-interceptor:4.9.3")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")
    api("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")

    api("io.insert-koin:koin-core:$koinVersion")
    api("io.insert-koin:koin-android:$koinVersion")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.annotation:annotation:1.3.0")

    api("net.zetetic:android-database-sqlcipher:4.4.0")
    api("androidx.sqlite:sqlite-ktx:2.1.0")
}