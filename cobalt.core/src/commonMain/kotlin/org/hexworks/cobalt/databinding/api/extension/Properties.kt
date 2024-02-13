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
import org.hexworks.cobalt.databinding.internal.collections.DefaultListProperty
import org.hexworks.cobalt.databinding.internal.collections.DefaultMapProperty
import org.hexworks.cobalt.databinding.internal.collections.DefaultPropertyListProperty
import org.hexworks.cobalt.databinding.internal.collections.DefaultPropertyMapProperty
import org.hexworks.cobalt.databinding.internal.collections.DefaultPropertySetProperty
import org.hexworks.cobalt.databinding.internal.collections.DefaultSetProperty
import org.hexworks.cobalt.databinding.internal.property.DefaultProperty
import org.hexworks.cobalt.databinding.internal.property.InternalProperty
import kotlin.jvm.JvmName
import kotlin.jvm.JvmOverloads

/**
 * Creates a new [Property] from the given object of type [T].
 */
@JvmOverloads
fun <T> T.toProperty(
    validator: PropertyValidator<T> = { _, _ -> true },
    name: String? = null,
): Property<T> = DefaultProperty(this, name, validator)

// Iterable / Collection

@JvmName("toIterableProperty")
@JvmOverloads
fun <T> Iterable<T>.toProperty(
    validator: PropertyValidator<Iterable<T>> = { _, _ -> true },
    name: String? = null,
): ListProperty<T> = DefaultListProperty(this.toPersistentList(), name, validator)

@JvmName("toCollectionProperty")
@JvmOverloads
fun <T> Collection<T>.toProperty(
    validator: PropertyValidator<Collection<T>> = { _, _ -> true },
    name: String? = null,
): ListProperty<T> = DefaultListProperty(this.toPersistentList(), name, validator)

// List
@JvmName("toListProperty")
@JvmOverloads
fun <T> List<T>.toProperty(
    validator: PropertyValidator<List<T>> = { _, _ -> true },
    name: String? = null,
): ListProperty<T> = DefaultListProperty(this.toPersistentList(), name, validator)

@JvmName("toPropertyListProperty")
@JvmOverloads
fun <T, V : ObservableValue<T>> List<V>.toProperty(
    validator: PropertyValidator<List<V>> = { _, _ -> true },
    name: String? = null,
): ListProperty<V> = DefaultPropertyListProperty(this.toPersistentList(), name, validator)

// Map
@JvmName("toMapProperty")
@JvmOverloads
fun <K : Any, V> Map<K, V>.toProperty(
    validator: PropertyValidator<Map<K, V>> = { _, _ -> true },
    name: String? = null,
): MapProperty<K, V> = DefaultMapProperty(this.toPersistentMap(), name, validator)

@JvmName("toPropertyMapProperty")
@JvmOverloads
fun <K : Any, V, P : Property<V>> Map<K, P>.toProperty(
    validator: PropertyValidator<Map<K, P>> = { _, _ -> true },
    name: String? = null,
): MapProperty<K, P> = DefaultPropertyMapProperty(this.toPersistentMap(), name, validator)

@JvmName("toSetProperty")
@JvmOverloads
fun <T> Set<T>.toProperty(
    validator: PropertyValidator<Set<T>> = { _, _ -> true },
    name: String? = null,
): SetProperty<T> = DefaultSetProperty(this.toPersistentSet(), name, validator)

@JvmName("toPropertySetProperty")
@JvmOverloads
fun <T, V : ObservableValue<T>> Set<V>.toProperty(
    validator: PropertyValidator<Set<V>> = { _, _ -> true },
    name: String? = null,
): SetProperty<V> = DefaultPropertySetProperty(this.toPersistentSet(), name, validator)

/**
 * Creates a new [InternalProperty] from the given object of type [T].
 */
internal fun <T> T.toInternalProperty(
    validator: PropertyValidator<T> = { _, _ -> true },
    name: String? = null,
): InternalProperty<T> = DefaultProperty(this, name, validator)
