import Libraries.kotlinLogging
import Libraries.kotlinReflect
import Libraries.kotlinStdLibCommon
import Libraries.kotlinxCollectionsImmutable
import Libraries.kotlinxCoroutines
import Libraries.uuid
import TestLibs.kotlinTestAnnotationsCommon
import TestLibs.kotlinTestCommon
import TestLibs.kotlinTestJs
import TestLibs.kotlinTestJunit

plugins {
    kotlin("multiplatform")
    id("maven-publish")
    id("signing")
}

kotlin {

    jvm {
        jvmTarget(JavaVersion.VERSION_1_8)
        withJava()
    }

    js {
        browser()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(kotlinStdLibCommon)

                api(kotlinxCoroutines)
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
        val jvmMain by getting {
            dependencies {
                api(kotlin("stdlib-jdk8"))
            }
        }
        val jsMain by getting {
            dependencies {
                api(kotlin("stdlib-js"))
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(kotlinTestJunit)
            }
        }
        val jsTest by getting {
            dependencies {
                implementation(kotlinTestJs)
            }
        }
    }
}

publishing {
    publishWith(
        project = project,
        module = "cobalt.core",
        desc = "Multiplatform utilities library for Kotlin."
    )
}

signing {
    isRequired = true
    sign(publishing.publications)
}
