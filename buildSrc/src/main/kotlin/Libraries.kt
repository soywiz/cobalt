import Versions.kotlinLoggingVersion
import Versions.kotlinxCollectionsImmutableVersion
import Versions.kotlinxCoroutinesVersion
import Versions.uuidVersion

object Libraries {

    const val kotlinReflect = "org.jetbrains.kotlin:kotlin-reflect"
    const val kotlinxCoroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinxCoroutinesVersion"
    const val kotlinxCollectionsImmutable =
        "org.jetbrains.kotlinx:kotlinx-collections-immutable:$kotlinxCollectionsImmutableVersion"

    const val uuid = "com.benasher44:uuid:$uuidVersion"
    const val kotlinLogging = "io.github.microutils:kotlin-logging:$kotlinLoggingVersion"

    // TEST
    const val kotlinTestCommon = "org.jetbrains.kotlin:kotlin-test-common"
    const val kotlinTestAnnotationsCommon = "org.jetbrains.kotlin:kotlin-test-annotations-common"
    const val kotlinTestJunit = "org.jetbrains.kotlin:kotlin-test-junit"
    const val kotlinTestJs = "org.jetbrains.kotlin:kotlin-test-js"
}
