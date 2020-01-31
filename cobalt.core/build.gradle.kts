plugins {
    kotlinMultiplatform
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
            commonMainApi(kotlinStdLibCommon)
            commonMainApi(kotlinxCoroutinesCommon)
            commonMainApi(kotlinReflect)

            commonMainApi(uuid)

            jvmMainApi(kotlinStdLibJdk8)
            jvmMainApi(kotlinxCoroutines)

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