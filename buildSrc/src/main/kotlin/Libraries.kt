import Versions.kotlinxCollectionsImmutableVersion
import Versions.kotlinxCoroutinesVersion
import Versions.uuidVersion
import Versions.kotlinLoggingVersion

object Libraries {

    const val kotlinStdLibCommon = "org.jetbrains.kotlin:kotlin-stdlib-common"
    const val kotlinReflect = "org.jetbrains.kotlin:kotlin-reflect"

    const val kotlinxCoroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinxCoroutinesVersion"
    const val kotlinxCollectionsImmutable =
        "org.jetbrains.kotlinx:kotlinx-collections-immutable:$kotlinxCollectionsImmutableVersion"

    const val uuid = "com.benasher44:uuid:$uuidVersion"
    const val kotlinLogging = "io.github.microutils:kotlin-logging:$kotlinLoggingVersion"
}
