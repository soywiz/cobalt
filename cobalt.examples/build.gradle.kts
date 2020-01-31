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
            jsMainApi(kotlinStdLibJs)
        }

        with(Projects) {

            commonMainApi(cobaltDatabinding)
            commonMainApi(cobaltDatatypes)
            commonMainApi(cobaltEvents)
            commonMainApi(cobaltHtml)
            commonMainApi(cobaltLogging)
            commonMainApi(cobaltNetworking)
        }
    }
}