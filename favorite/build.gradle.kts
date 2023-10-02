plugins {
    id("com.android.dynamic-feature")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("kotlin-android")
    id("kotlin-parcelize")
}
android {
    namespace = "com.rbs.favorite"
    compileSdk = 33

    defaultConfig {
        minSdk = 26
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

//    buildTypes {
//        release {
//            isMinifyEnabled = false
//            proguardFiles(
//                getDefaultProguardFile("proguard-android-optimize.txt"),
//                "proguard-rules.pro"
//            )
//        }
//        tasks.named("exportReleaseConsumerProguardFiles").configure {
//            dependsOn("extractProguardFiles")
//        }
//        tasks.named("exportReleaseConsumerProguardFiles").configure {
//            dependsOn("extractProguardFiles")
//        }
//        tasks.named("exportReleaseConsumerProguardFiles") {
//            mustRunAfter("extractProguardFiles")
//        }
//        tasks.configureEach {
//            if (name == "exportReleaseConsumerProguardFiles") {
//                mustRunAfter(tasks.extractProguardFiles)
//            }
//        }
//        tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
//            dependsOn(tasks.withType<AntlrTask>())
//        }
//        tasks.withType<Jar>().configureEach {
//            dependsOn(tasks.withType<AntlrTask>())
//        }
//    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":app"))
    implementation(project(":core"))
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.recyclerview:recyclerview:1.3.1")
    testImplementation("org.testng:testng:6.9.6")
}