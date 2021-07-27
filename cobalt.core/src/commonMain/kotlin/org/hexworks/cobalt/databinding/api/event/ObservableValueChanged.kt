package org.hexworks.cobalt.databinding.api.event

import org.hexworks.cobalt.databinding.api.value.ObservableValue
import org.hexworks.cobalt.events.api.Event

/**
 * Fired when an [ObservableValue] **changes** its value.
 */
data class ObservableValueChanged<out T : Any>(
    val oldValue: T,
    val newValue: T,
    val observableValue: ObservableValue<T>,
    val type: ChangeType,
    override val emitter: Any,
    override val trace: Iterable<Event> = listOf()
) : Event {
    companion object
}

sealed class ChangeType
object ScalarChange : ChangeType() {
    override fun toString() = "ChangeType.ScalarChange"
}

sealed class ListChange : ChangeType()
data class ListAdd<T : Any>(val element: T) : ListChange()
data class ListAddAt<T : Any>(val index: Int, val element: T) : ListChange()
data class ListRemove<T : Any>(val element: T) : ListChange()
data class ListRemoveAt(val index: Int) : ListChange()
data class ListSet<T : Any>(val index: Int, val element: T) : ListChange()
data class ListAddAll<T : Any>(val elements: Collection<T>) : ListChange()
data class ListAddAllAt<T : Any>(val index: Int, val c: Collection<T>) : ListChange()
data class ListRemoveAll<T : Any>(val elements: Collection<T>) : ListChange()
data class ListRemoveAllWhen<T : Any>(val predicate: (T) -> Boolean) : ListChange()
data class ListRetainAll<T : Any>(val elements: Collection<T>) : ListChange()
data class ListPropertyChange<T : Any>(val changeEvent: ObservableValueChanged<T>) : ListChange()
object ListClear : ListChange() {
    override fun toString() = "ChangeType.ListChange.ListClear"
}

sealed class SetChange : ChangeType()
data class SetAdd<T : Any>(val element: T) : SetChange()
data class SetRemove<T : Any>(val element: T) : SetChange()
data class SetAddAll<T : Any>(val elements: Collection<T>) : SetChange()
data class SetRemoveAll<T : Any>(val elements: Collection<T>) : SetChange()
data class SetRemoveAllWhen<T : Any>(val predicate: (T) -> Boolean) : SetChange()
data class SetRetainAll<T : Any>(val elements: Collection<T>) : SetChange()
data class SetPropertyChange<T : Any>(val changeEvent: ObservableValueChanged<T>) : SetChange()
object SetClear : SetChange() {
    override fun toString() = "ChangeType.SetChange.SetClear"
}

sealed class MapChange : ChangeType()
data class MapPut<K : Any, V : Any>(val key: K, val value: V) : MapChange()
data class MapPutAll<K : Any, V : Any>(val m: Map<out K, V>) : MapChange()
data class MapRemove<K : Any>(val key: K) : MapChange()
data class MapRemoveWithValue<K : Any, V : Any>(val key: K, val value: V) : MapChange()
data class MapPropertyChange<T : Any>(val changeEvent: ObservableValueChanged<T>) : MapChange()
object MapClear : MapChange() {
    override fun toString() = "ChangeType.MapChange.MapClear"
}


