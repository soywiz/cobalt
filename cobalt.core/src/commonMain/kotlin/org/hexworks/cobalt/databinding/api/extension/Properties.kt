@file:JvmName("Properties")

package org.hexworks.cobalt.databinding.api.extension

import kotlinx.collections.immutable.toPersistentList
import kotlinx.collections.immutable.toPersistentMap
import kotlinx.collections.immutable.toPersistentSet
import org.hexworks.cobalt.databinding.api.collection.ListProperty
import org.hexworks.cobalt.databinding.api.collection.MapProperty
import org.hexworks.cobalt.databinding.api.collection.SetProperty
import org.hexworks.cobalt.databinding.api.property.Property
import org.hexworks.cobalt.databinding.api.property.PropertyValidator
import org.hexworks.cobalt.databinding.api.value.ObservableValue
import org.hexworks.cobalt.databinding.internal.collections.*
import org.hexworks.cobalt.databinding.internal.property.DefaultProperty
import org.hexworks.cobalt.databinding.internal.property.InternalProperty
import kotlin.jvm.JvmName
import kotlin.jvm.JvmOverloads

/**
 * Creates a new [Property] from the given object of type [T].
 */
@JvmOverloads
fun <T : Any> T.toProperty(
    validator: PropertyValidator<T> = { _, _ -> true },
    optionalName: String? = null,
): Property<T> = DefaultProperty(this, optionalName, validator)

// Iterable / Collection

@JvmName("toIterableProperty")
@JvmOverloads
fun <T : Any> Iterable<T>.toProperty(
    validator: PropertyValidator<Iterable<T>> = { _, _ -> true },
    optionalName: String? = null,
): ListProperty<T> = DefaultListProperty(this.toPersistentList(), optionalName, validator)

@JvmName("toCollectionProperty")
@JvmOverloads
fun <T : Any> Collection<T>.toProperty(
    validator: PropertyValidator<Collection<T>> = { _, _ -> true },
    optionalName: String? = null,
): ListProperty<T> = DefaultListProperty(this.toPersistentList(), optionalName, validator)

// List
@JvmName("toListProperty")
@JvmOverloads
fun <T : Any> List<T>.toProperty(
    validator: PropertyValidator<List<T>> = { _, _ -> true },
    optionalName: String? = null,
): ListProperty<T> = DefaultListProperty(this.toPersistentList(), optionalName, validator)

@JvmName("toPropertyListProperty")
@JvmOverloads
fun <T : Any, V : ObservableValue<T>> List<V>.toProperty(
    validator: PropertyValidator<List<V>> = { _, _ -> true },
    optionalName: String? = null,
): ListProperty<V> = DefaultPropertyListProperty(this.toPersistentList(), optionalName, validator)

// Map
@JvmName("toMapProperty")
@JvmOverloads
fun <K : Any, V : Any> Map<K, V>.toProperty(
    validator: PropertyValidator<Map<K, V>> = { _, _ -> true },
    optionalName: String? = null,
): MapProperty<K, V> = DefaultMapProperty(this.toPersistentMap(), optionalName, validator)

@JvmName("toPropertyMapProperty")
@JvmOverloads
fun <K : Any, V : Any, P : Property<V>> Map<K, P>.toProperty(
    validator: PropertyValidator<Map<K, P>> = { _, _ -> true },
    optionalName: String? = null,
): MapProperty<K, P> = DefaultPropertyMapProperty(this.toPersistentMap(), optionalName, validator)


@JvmName("toSetProperty")
@JvmOverloads
fun <T : Any> Set<T>.toProperty(
    validator: PropertyValidator<Set<T>> = { _, _ -> true },
    optionalName: String? = null,
): SetProperty<T> = DefaultSetProperty(this.toPersistentSet(), optionalName, validator)

@JvmName("toPropertySetProperty")
@JvmOverloads
fun <T : Any, V : ObservableValue<T>> Set<V>.toProperty(
    validator: PropertyValidator<Set<V>> = { _, _ -> true },
    optionalName: String? = null,
): SetProperty<V> = DefaultPropertySetProperty(this.toPersistentSet(), optionalName, validator)


/**
 * Creates a new [InternalProperty] from the given object of type [T].
 */
internal fun <T : Any> T.toInternalProperty(
    validator: PropertyValidator<T> = { _, _ -> true },
    optionalName: String? = null,
): InternalProperty<T> = DefaultProperty(this, optionalName, validator)
