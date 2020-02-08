plugins {
    kotlin("multiplatform")
}

group = "org.hexworks.cobalt"

kotlin {

    jvm {
        jvmTarget(JavaVersion.VERSION_1_8)
        withJava()
    }

    js {
        browser()
    }

    dependencies {

        with(Libs) {
            commonMainApi(kotlinxCollectionsImmutable)
        }

        with(Projects) {
            commonMainApi(cobaltCore)
            commonMainApi(cobaltDatatypes)
            commonMainApi(cobaltEvents)
            commonMainApi(cobaltLogging)
        }

        with(TestLibs) {
            commonTestImplementation(kotlinTestCommon)
            commonTestImplementation(kotlinTestAnnotationsCommon)

            jvmTestImplementation(kotlinTestJunit)

            jsTestImplementation(kotlinTestJs)
        }
    }
}