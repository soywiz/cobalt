@file:JvmName("Properties")

package org.hexworks.cobalt.databinding.api.extension

import org.hexworks.cobalt.core.extensions.Predicate
import org.hexworks.cobalt.databinding.api.collection.ListProperty
import org.hexworks.cobalt.databinding.api.collection.MapProperty
import org.hexworks.cobalt.databinding.api.collection.SetProperty
import org.hexworks.cobalt.databinding.api.property.Property
import org.hexworks.cobalt.databinding.api.property.PropertyValidator
import org.hexworks.cobalt.databinding.internal.collections.DefaultListProperty
import org.hexworks.cobalt.databinding.internal.collections.DefaultMapProperty
import org.hexworks.cobalt.databinding.internal.collections.DefaultSetProperty
import org.hexworks.cobalt.databinding.internal.property.DefaultProperty
import org.hexworks.cobalt.databinding.internal.property.InternalProperty
import kotlin.jvm.JvmName

/**
 * Creates a new [Property] from the given object [obj].
 */
fun <T : Any> createPropertyFrom(
    obj: T,
    validator: PropertyValidator<T> = { _, _ -> true }
): Property<T> =
    DefaultProperty(
        initialValue = obj,
        validator = validator
    )

/**
 * Creates a new [Property] from the given object of type [T].
 */
fun <T : Any> T.toProperty(
    validator: PropertyValidator<T> = { _, _ -> true }
): Property<T> =
    createPropertyFrom(
        obj = this,
        validator = validator
    )

fun <T : Any> List<T>.toProperty(
    validator: PropertyValidator<List<T>> = { _, _ -> true }
): ListProperty<T> = DefaultListProperty(this, validator)

fun <K : Any, V : Any> Map<K, V>.toProperty(
    validator: PropertyValidator<Map<K, V>> = { _, _ -> true }
): MapProperty<K, V> = DefaultMapProperty(this, validator)

fun <T : Any> Set<T>.toProperty(
    validator: PropertyValidator<Set<T>> = { _, _ -> true }
): SetProperty<T> = DefaultSetProperty(this, validator)

fun <T : Any> Iterable<T>.toProperty(
    validator: PropertyValidator<Iterable<T>> = { _, _ -> true }
): ListProperty<T> = DefaultListProperty(this.toList(), validator)

fun <T : Any> Collection<T>.toProperty(
    validator: PropertyValidator<Collection<T>> = { _, _ -> true }
): ListProperty<T> = DefaultListProperty(this.toList(), validator)

/**
 * Creates a new [InternalProperty] from the given object of type [T].
 */
internal fun <T : Any> T.toInternalProperty(
    validator: PropertyValidator<T> = { _, _ -> true }
): InternalProperty<T> =
    createPropertyFrom(
        obj = this,
        validator = validator
    ) as InternalProperty<T>
