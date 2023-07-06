import Libraries.kotlinLogging
import Libraries.kotlinReflect
import Libraries.kotlinTestAnnotationsCommon
import Libraries.kotlinTestCommon
import Libraries.kotlinTestJs
import Libraries.kotlinTestJunit
import Libraries.kotlinxCollectionsImmutable
import Libraries.uuid

plugins {
    kotlin("multiplatform")
    id("maven-publish")
    id("signing")
}

kotlin {

    jvm {
        withJava()
        compilations.all {
            kotlinOptions {
                apiVersion = "1.8"
                languageVersion = "1.8"
                jvmTarget = "11"
            }
        }
    }

    js(IR) {
        browser {
            testTask {
                useKarma {
                    useChromeHeadless()
                }
            }
        }
        nodejs()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(kotlinReflect)
                api(kotlinxCollectionsImmutable)

                api(uuid)
                api(kotlinLogging)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlinTestCommon)
                implementation(kotlinTestAnnotationsCommon)
            }
        }
        val jvmMain by getting {}
        val jvmTest by getting {
            dependencies {
                implementation(kotlinTestJunit)
            }
        }

        val jsMain by getting {}
        val jsTest by getting {
            dependencies {
                implementation(kotlinTestJs)
            }
        }
    }
    jvmToolchain(11)
}

publishing {
    publishWith(
        project = project,
        module = "cobalt.core",
        desc = "Multiplatform utilities library for Kotlin."
    )
}

signing {
    isRequired = false
    sign(publishing.publications)
}
