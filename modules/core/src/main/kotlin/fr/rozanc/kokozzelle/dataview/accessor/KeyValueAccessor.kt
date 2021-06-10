package fr.rozanc.kokozzelle.dataview.accessor

import fr.rozanc.kokozzelle.datatable.ColumnExpression
import kotlin.reflect.full.declaredMemberProperties

class KeyValueAccessor(private val expression: ColumnExpression) : ValueAccessor {

    override fun accessValue(dataContainer: Any): Any? {
        if (dataContainer is Map<*, *>) {
            return dataContainer[expression.string]
        }
        return (dataContainer::class).declaredMemberProperties
                .first { it.name == expression.string }
                .call(dataContainer)
    }
}
