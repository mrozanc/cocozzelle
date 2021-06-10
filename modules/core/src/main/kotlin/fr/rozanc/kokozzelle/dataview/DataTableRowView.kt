package fr.rozanc.kokozzelle.dataview

import fr.rozanc.kokozzelle.dataview.accessor.ValueAccessor
import java.lang.IllegalArgumentException

class DataTableRowView(
    private val valuesHolder: Any,
    private val accessors: Map<String, ValueAccessor> = emptyMap()
) {
    fun columnNames() = accessors.keys

    fun values() = accessors.asSequence().map { it.key to it.value.accessValue(valuesHolder) }

    operator fun get(name: String): Any? {
        if (accessors.containsKey(name)) {
            return accessors[name]!!.accessValue(valuesHolder)
        }
        throw IllegalArgumentException("No accessors defined for name \"$name\". Available accessors: ${columnNames()}.")
    }
}
