// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.compose.compiler) apply false
    id("com.google.devtools.ksp") version "2.0.20-1.0.24" apply false
    id("com.google.dagger.hilt.android") version "2.48" apply false
    id("com.google.gms.google-services") version "4.4.2" apply false
    kotlin("jvm") version "2.0.20"
    kotlin("plugin.serialization") version "2.0.0-RC1"

}
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.0.2")// Verifica que uses la versión correcta de gradle
        classpath("com.google.gms:google-services:4.3.15") // Classpath del plugin de Google Services
        classpath(kotlin("gradle-plugin", version = "2.0.0-RC1"))
    }
}