plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.hypermagik.spectrum"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.hypermagik.spectrum"
        minSdk = 21          // Android 5.0 – required for Note 3
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        // Important for vector drawables on old devices
        vectorDrawables.useSupportLibrary = true
    }

    // your existing signingConfigs / buildTypes / buildFeatures can stay
}

    signingConfigs {
        create("spectrum") {
            storeFile = file("spectrum.jks")
            storePassword = "spectrum"
            keyAlias = "spectrum"
            keyPassword = "spectrum"
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            signingConfig = signingConfigs.getByName("spectrum")
        }

        debug {
            signingConfig = signingConfigs.getByName("spectrum")
        }
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    // Core + UI
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)        // AppCompatActivity, ActionBar, etc.
    implementation(libs.androidx.constraintlayout) // ConstraintLayout
    implementation(libs.material)                  // Material components (Buttons, Cards, TextInput, etc.)

    // Often useful
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    implementation("androidx.cardview:cardview:1.0.0")
    implementation("androidx.fragment:fragment-ktx:1.8.2")

    implementation(project(":lib"))
}
