plugins {
    id("com.android.application")
}

android {
    namespace = "ru.vik.mathschoolhelper"
    compileSdk = 34

    defaultConfig {
        applicationId = "ru.vik.mathschoolhelper"
        minSdk = 25
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

    packagingOptions {
        resources.excludes.add("META-INF/*")
    }
}

dependencies {

    // Стандартные библиотеки
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // Retrofit - работа с сетью и REST-запросами
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // Picasso - работа с изображениями
    implementation ("com.squareup.picasso:picasso:2.8")

    // Room - локальная база данных от Google
    implementation ("androidx.room:room-runtime:2.6.0")
    annotationProcessor ("androidx.room:room-compiler:2.6.0")

    // Jsoup - библиотека парсинга сайтов
    implementation ("org.jsoup:jsoup:1.15.2")

    // YouTube API v3 - библиотека API Youtube
    implementation("com.google.apis:google-api-services-youtube:v3-rev222-1.25.0")

    // Gson - библиотека для работы с Json файлами
    implementation ("com.google.http-client:google-http-client-gson:1.41.0")

    implementation ("com.google.guava:listenablefuture:9999.0-empty-to-avoid-conflict-with-guava")
}