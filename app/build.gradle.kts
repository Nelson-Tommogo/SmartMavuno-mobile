plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.googleGmsGoogleServices)
    alias(libs.plugins.googleFirebaseCrashlytics)
}

android {
    namespace = "com.example.smartmavuno"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.smartmavuno"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
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
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.constraintlayout.compose)
    
    //noinspection UseTomlInstead
    implementation("io.coil-kt:coil-compose:1.4.0")
    //noinspection UseTomlInstead
    implementation ("io.coil-kt:coil-compose:1.4.0")

    implementation(libs.play.services.vision)
    // Logging
    //noinspection UseTomlInstead
    implementation("com.jakewharton.timber:timber:5.0.1")
    // Coroutines
    //noinspection UseTomlInstead
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    // State Management
    //noinspection UseTomlInstead
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation(libs.firebase.auth.ktx)
    implementation(libs.firebase.database)
    implementation(libs.firebase.firestore.ktx)
    implementation(libs.firebase.database.ktx)
    implementation(libs.firebase.storage.ktx)
    implementation(libs.firebase.functions.ktx)
    implementation(libs.firebase.ml.vision)
    implementation(libs.firebase.messaging.ktx)
    implementation(libs.firebase.crashlytics.ktx)
    implementation(libs.firebase.analytics.ktx)
    // Testing
    //noinspection UseTomlInstead
    testImplementation("io.mockk:mockk:1.12.0")
    // Material Design Components
    //noinspection UseTomlInstead


    implementation ("androidx.compose.ui:ui:1.6.7")
    //noinspection UseTomlInstead
    implementation ("androidx.compose.material3:material3:1.2.1")
    //noinspection UseTomlInstead
    implementation ("androidx.navigation:navigation-compose:2.7.7")

    androidTestImplementation(libs.androidx.espresso.core)
    //noinspection UseTomlInstead
    androidTestImplementation("androidx.test.espresso:espresso-contrib:3.5.1")
    //noinspection UseTomlInstead
    androidTestImplementation("androidx.test.espresso:espresso-intents:3.5.1")
    //noinspection UseTomlInstead
    androidTestImplementation("androidx.test.espresso:espresso-accessibility:3.5.1")
    //noinspection UseTomlInstead
    androidTestImplementation("androidx.test.espresso:espresso-web:3.5.1")

    //noinspection UseTomlInstead
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //noinspection UseTomlInstead
    implementation("com.google.android.material:material:1.12.0")
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}