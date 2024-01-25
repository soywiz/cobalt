import Libraries.KORGE_FOUNDATION
import Libraries.KOTLINX_COLLECTIONS_IMMUTABLE
import Libraries.KOTLIN_REFLECT

plugins {
    kotlin("multiplatform")
    id("maven-publish")
    id("signing")
}

val javaVersion = JavaVersion.VERSION_11

java {
    sourceCompatibility = javaVersion
    targetCompatibility = javaVersion
}

kotlin {

    jvm {
        withJava()
        compilations.all {
            kotlinOptions {
                apiVersion = "1.9"
                languageVersion = "1.9"
                jvmTarget = javaVersion.toString()

            }
        }
    }

    js(IR) {
        compilations.all {
            kotlinOptions {
                sourceMap = true
                moduleKind = "umd"
                metaInfo = true
            }
        }
        browser {
            testTask {
                useMocha()
            }
        }
        nodejs()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(KOTLIN_REFLECT)
                api(KOTLINX_COLLECTIONS_IMMUTABLE)

                api(KORGE_FOUNDATION)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
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
    isRequired = false
    sign(publishing.publications)
}
