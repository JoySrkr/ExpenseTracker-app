plugins {
    alias(libs.plugins.androidApplication)
    id("realm-android")
}

android {
    namespace = "com.example.expancetracker"
    compileSdk = 34

    buildFeatures {
        viewBinding {
            enable = true;
        }
    }

    defaultConfig {
        applicationId = "com.example.expancetracker"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation("com.github.AnyChart:AnyChart-Android:1.1.5")
    implementation("androidx.core:core:1.10.1")

}