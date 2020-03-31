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
    val type: ChangeType = ScalarChange,
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
object ListClear : ListChange() {
    override fun toString() = "ChangeType.ListChange.ListClear"
}


