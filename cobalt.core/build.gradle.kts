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
            commonMainApi(kotlinStdLibCommon)
            
            commonMainApi(kotlinxCoroutinesCommon)
            commonMainApi(kotlinReflect)

            commonMainApi(uuid)
            commonMainApi(kotlinxCollectionsImmutable)

            jvmMainApi(kotlinStdLibJdk8)
            jvmMainApi(kotlinxCoroutines)
            jvmMainApi(slf4jApi)
            jvmMainApi(logbackClassic)

            jsMainApi(kotlinStdLibJs)
            jsMainApi(kotlinxCoroutinesJs)
        }

        with(TestLibs) {
            commonTestApi(kotlinTestCommon)
            commonTestApi(kotlinTestAnnotationsCommon)
            commonTestApi(kotlinxCoroutinesTest)

            jvmTestApi(kotlinTestJunit)
            jsTestApi(kotlinTestJs)
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
