// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
}

val elmslieVersion by extra("3.0.0-alpha09")
val elmslieComposeVersion by extra("2.1.1")
