@file:Suppress("UNUSED_PARAMETER")

package org.hexworks.cobalt.databinding.internal.collections

import kotlinx.collections.immutable.ImmutableCollection
import kotlinx.collections.immutable.ImmutableSet
import kotlinx.collections.immutable.PersistentMap
import kotlinx.collections.immutable.toPersistentMap
import org.hexworks.cobalt.core.extensions.Predicate
import org.hexworks.cobalt.databinding.api.collection.MapProperty
import org.hexworks.cobalt.databinding.internal.property.base.BaseProperty

@Suppress("UNCHECKED_CAST")
class DefaultMapProperty<K : Any, V : Any>(
        initialValue: Map<K, V>,
        validator: Predicate<Map<K, V>> = { true }
) : BaseProperty<PersistentMap<K, V>>(
        initialValue = initialValue.toPersistentMap(),
        validator = validator), MapProperty<K, V> {

    override val entries: ImmutableSet<Map.Entry<K, V>>
        get() = value.entries

    override val keys: ImmutableSet<K>
        get() = value.keys

    override val size: Int
        get() = value.size

    override val values: ImmutableCollection<V>
        get() = value.values

    override fun containsKey(key: K) = value.containsKey(key)

    override fun containsValue(value: V) = this.value.containsValue(value)

    override fun get(key: K): V? = value[key]

    override fun isEmpty() = value.isEmpty()

    override fun clear(): PersistentMap<K, V> {
        return updateCurrentValue { it.clear() }
    }

    override fun put(key: K, value: V): PersistentMap<K, V> {
        return updateCurrentValue { it.put(key, value) }
    }

    override fun putAll(m: Map<out K, V>): PersistentMap<K, V> {
        return updateCurrentValue { it.putAll(m) }
    }

    override fun remove(key: K): PersistentMap<K, V> {
        return updateCurrentValue { it.remove(key) }
    }

    override fun remove(key: K, value: V): PersistentMap<K, V> {
        return updateCurrentValue { it.remove(key, value) }
    }

    override fun builder() = value.builder()

}
