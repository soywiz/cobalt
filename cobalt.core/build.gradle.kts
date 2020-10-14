import Libs.kotlinReflect
import Libs.kotlinStdLibCommon
import Libs.kotlinxCollectionsImmutable
import Libs.kotlinxCoroutines
import Libs.logbackClassic
import Libs.slf4jApi
import Libs.uuid
import TestLibs.kotlinTestAnnotationsCommon
import TestLibs.kotlinTestCommon
import TestLibs.kotlinTestJs
import TestLibs.kotlinTestJunit
import TestLibs.kotlinxCoroutinesTest

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

                api(logbackClassic)
                api(slf4jApi)
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
        desc = "Core utilities for Cobalt."
    )
}

if (!version.toString().endsWith("SNAPSHOT")) {
    signing {
        isRequired = false
        sign(publishing.publications)
    }
}
