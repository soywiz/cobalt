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

    dependencies {

        with(Libs) {
            commonMainApi(kotlinxCollectionsImmutable)
        }

        with(Projects) {
            commonMainApi(cobaltCore)
        }

        with(TestLibs) {
            commonTestImplementation(kotlinTestCommon)
            commonTestImplementation(kotlinTestAnnotationsCommon)
            jvmTestImplementation(kotlinTestJunit)
            jsTestImplementation(kotlinTestJs)
        }
    }
}

publishing {
    publishWith(
        project = project,
        module = "cobalt.datatypes",
        desc = "Datatypes for Cobalt."
    )
}

signing {
    isRequired = false
    sign(publishing.publications)
}