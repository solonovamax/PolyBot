/*
 * PolyBot - A Discord bot for the Polyhedral Development discord server
 * Copyright (c) 2022-2022 solonovamax <solonovamax@12oclockpoint.com>
 *
 * The file build.gradle.kts is part of PolyBot
 * Last modified on 20-08-2022 05:46 p.m.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * POLYBOT IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

@file:Suppress("SuspiciousCollectionReassignment")

plugins {
    java
    kotlin("jvm")
    kotlin("plugin.noarg")
    kotlin("plugin.serialization")
    id("com.google.devtools.ksp")
}

repositories {
    maven("https://m2.dv8tion.net/releases")
    
    maven("https://jitpack.io/")
    maven("https://m2.chew.pro/releases")
}

kotlin {
    explicitApi()
    target {
        compilations.all {
            kotlinOptions {
                freeCompilerArgs += "-opt-in=kotlin.RequiresOptIn"
            }
        }
    }
}

dependencies {
    // Common Subproject
    api(project(":common"))
    
    implementation(libs.ksp.service)
    ksp(libs.ksp.service)
    
    // Kotlin
    api(libs.bundles.kotlin)
    // Kotlin Serialization
    api(libs.bundles.kotlinx.serialization)
    
    // Kotlin Coroutines
    api(libs.bundles.kotlinx.coroutines)
    // Kotlin Datetime
    api(libs.kotlinx.datetime)
    
    // Kodein Dependency Injection
    api(libs.kodein)
    
    // Konf
    api(libs.konf)
    
    // Jetbrains Annotations
    api(libs.jetbrains.annotations)
    
    // JDA
    api(libs.jda)
    // Discord webhooks
    api(libs.discord.webhooks)
    // JDA Kotlin extensions
    api(libs.jda.ktx)
    // JDA utilities
    api(libs.bundles.jda.utilities)
    
    // Cloud
    api(libs.bundles.cloud)
    
    // SLF4J
    api(libs.slf4j)
    // SLF4J extension library
    api(libs.slf4k)
    
    api(libs.bundles.strata)
    
    implementation(libs.logback)
    
    implementation(libs.dsiutils)
    
    // Testing
    testApi(libs.bundles.junit)
    testApi(kotlin("test"))
}

noArg {
    invokeInitializers = true
    annotation("kotlinx.serialization.Serializable")
}