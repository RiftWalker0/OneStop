plugins {
  id("com.android.application")
  id("org.jetbrains.kotlin.android")
}

android {
  namespace = "com.onestop"
  compileSdk = 35

  defaultConfig {
    applicationId = "com.onestop"
    minSdk = 24
    targetSdk = 35
    versionCode = 1300
    versionName = "1.3.0"
    vectorDrawables { useSupportLibrary = true }
  }

  buildTypes {
    getByName("debug") {
      isMinifyEnabled = false
    }
    release {
      isMinifyEnabled = true
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }

  buildFeatures {
    viewBinding = true
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }
  kotlinOptions {
    jvmTarget = "17"
  }

  packaging {
    resources.excludes.add("META-INF/*")
  }
}

dependencies {
  implementation("androidx.core:core-ktx:1.13.1")
  implementation("androidx.appcompat:appcompat:1.7.0")
  implementation("com.google.android.material:material:1.12.0")
  implementation("androidx.constraintlayout:constraintlayout:2.1.4")
  implementation("androidx.drawerlayout:drawerlayout:1.2.0")
  implementation("androidx.activity:activity:1.8.0")
  implementation("androidx.fragment:fragment:1.5.4")
  implementation("androidx.lifecycle:lifecycle-runtime:2.6.2")
  implementation("androidx.recyclerview:recyclerview:1.1.0")
}
