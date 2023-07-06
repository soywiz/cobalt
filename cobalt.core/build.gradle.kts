import Libraries.klogger
import Libraries.korio
import Libraries.kotlinReflect
import Libraries.kotlinxCollectionsImmutable
import org.jetbrains.kotlin.konan.target.HostManager

plugins {
    kotlin("multiplatform")
    id("maven-publish")
    id("signing")
}

kotlin {

    targets {
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
        jvm {
            // Intentionally left blank.
        }
        if (HostManager.hostIsMac) {
            macosX64()
            macosArm64()
            iosX64()
            iosArm64()
            iosSimulatorArm64()
            watchosArm32()
            watchosArm64()
            watchosX64()
            watchosSimulatorArm64()
            watchosDeviceArm64()
            tvosArm64()
            tvosX64()
            tvosSimulatorArm64()
        }
        if (HostManager.hostIsMingw || HostManager.hostIsMac) {
            mingwX64 {
                binaries.findTest(DEBUG)!!.linkerOpts = mutableListOf("-Wl,--subsystem,windows")
            }
        }
        if (HostManager.hostIsLinux || HostManager.hostIsMac) {
            linuxX64()
            linuxArm64()
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(kotlinReflect)
                api(kotlinxCollectionsImmutable)

                api(korio)
                api(klogger)
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
