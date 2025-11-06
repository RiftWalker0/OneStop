plugins { id("com.android.application") }

android {
    namespace = "com.onestop"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.onestop"
        minSdk = 24
        targetSdk = 34
        versionCode = 5
        versionName = "1.1.5"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        debug {}
    }

    buildFeatures { viewBinding = true }

    packaging { resources.excludes += setOf("META-INF/AL2.0","META-INF/LGPL2.1") }
}

dependencies {
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.drawerlayout:drawerlayout:1.2.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
}
