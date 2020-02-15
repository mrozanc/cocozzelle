package fr.rozanc.kokozzelle.dataview.accessor

import fr.rozanc.cocozzelle.datatable.ColumnExpression
import kotlin.reflect.full.declaredMemberProperties

class KeyValueAccessor : ValueAccessor {

    override fun accessValue(dataContainer: Any, expression: ColumnExpression): Any? {
        if (dataContainer is Map<*, *>) {
            return dataContainer[expression.string]
        }
        return (dataContainer::class).declaredMemberProperties
                .first { it.name == expression.string }
                .call(dataContainer)
    }
}
