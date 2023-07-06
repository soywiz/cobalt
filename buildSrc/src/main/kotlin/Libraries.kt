import Versions.korVersion
import Versions.kotlinxCollectionsImmutableVersion

object Libraries {

    const val kotlinReflect = "org.jetbrains.kotlin:kotlin-reflect"
    const val kotlinxCollectionsImmutable =
        "org.jetbrains.kotlinx:kotlinx-collections-immutable:$kotlinxCollectionsImmutableVersion"


    const val korio = "com.soywiz.korlibs.korio:korio:$korVersion"
    const val klogger = "com.soywiz.korlibs.klogger:klogger:$korVersion"

    // TEST
    const val kotlinTestCommon = "org.jetbrains.kotlin:kotlin-test-common"
    const val kotlinTestAnnotationsCommon = "org.jetbrains.kotlin:kotlin-test-annotations-common"
    const val kotlinTestJunit = "org.jetbrains.kotlin:kotlin-test-junit"
    const val kotlinTestJs = "org.jetbrains.kotlin:kotlin-test-js"
}
