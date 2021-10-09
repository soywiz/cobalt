package org.hexworks.cobalt.databinding.api.event

import org.hexworks.cobalt.databinding.api.value.ObservableValue
import org.hexworks.cobalt.events.api.Event

/**
 * Fired when an [ObservableValue] **changes** its value.
 */
public data class ObservableValueChanged<out T>(
    val oldValue: T,
    val newValue: T,
    val observableValue: ObservableValue<T>,
    val type: ChangeType,
    override val emitter: Any,
    override val trace: Iterable<Event> = listOf()
) : Event {
    companion object
}

public sealed class ChangeType
public object ScalarChange : ChangeType() {
    override fun toString() = "ChangeType.ScalarChange"
}

public sealed class ListChange : ChangeType()
public data class ListAdd<T>(val element: T) : ListChange()
public data class ListAddAt<T>(val index: Int, val element: T) : ListChange()
public data class ListRemove<T>(val element: T) : ListChange()
public data class ListRemoveAt(val index: Int) : ListChange()
public data class ListSet<T>(val index: Int, val element: T) : ListChange()
public data class ListAddAll<T>(val elements: Collection<T>) : ListChange()
public data class ListAddAllAt<T>(val index: Int, val c: Collection<T>) : ListChange()
public data class ListRemoveAll<T>(val elements: Collection<T>) : ListChange()
public data class ListRemoveAllWhen<T>(val predicate: (T) -> Boolean) : ListChange()
public data class ListRetainAll<T>(val elements: Collection<T>) : ListChange()
public data class ListPropertyChange<T>(val changeEvent: ObservableValueChanged<T>) : ListChange()
public object ListClear : ListChange() {
    override fun toString() = "ChangeType.ListChange.ListClear"
}

public sealed class SetChange : ChangeType()
public data class SetAdd<T>(val element: T) : SetChange()
public data class SetRemove<T>(val element: T) : SetChange()
public data class SetAddAll<T>(val elements: Collection<T>) : SetChange()
public data class SetRemoveAll<T>(val elements: Collection<T>) : SetChange()
public data class SetRemoveAllWhen<T>(val predicate: (T) -> Boolean) : SetChange()
public data class SetRetainAll<T>(val elements: Collection<T>) : SetChange()
public data class SetPropertyChange<T>(val changeEvent: ObservableValueChanged<T>) : SetChange()
public object SetClear : SetChange() {
    override fun toString() = "ChangeType.SetChange.SetClear"
}

public sealed class MapChange : ChangeType()
public data class MapPut<K : Any, V>(val key: K, val value: V) : MapChange()
public data class MapPutAll<K : Any, V>(val m: Map<out K, V>) : MapChange()
public data class MapRemove<K : Any>(val key: K) : MapChange()
public data class MapRemoveWithValue<K : Any, V>(val key: K, val value: V) : MapChange()
public data class MapPropertyChange<T>(val changeEvent: ObservableValueChanged<T>) : MapChange()
public object MapClear : MapChange() {
    override fun toString() = "ChangeType.MapChange.MapClear"
}
