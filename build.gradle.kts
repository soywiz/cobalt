plugins {
    kotlin("multiplatform") apply false
}

allprojects {

    group = "org.hexworks.cobalt"
    version = "2020.0.5-SNAPSHOT"

    repositories {
        mavenCentral()
        jcenter()
        kotlinx()
        jitpack()
        ktor()
        mavenLocal()
    }

}

subprojects {

    val emptySourcesJar by tasks.registering(Jar::class) {
        archiveClassifier.set("sources")
    }

    val emptyJavadocJar by tasks.registering(Jar::class) {
        archiveClassifier.set("javadoc")
    }
}
