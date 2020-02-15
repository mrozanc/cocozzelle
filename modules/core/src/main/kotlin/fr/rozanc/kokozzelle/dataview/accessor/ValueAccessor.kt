package fr.rozanc.kokozzelle.dataview.accessor

import fr.rozanc.cocozzelle.datatable.ColumnExpression

/**
 * Defines a way to retrieve a value from a given object.
 */
interface ValueAccessor {

    fun accessValue(dataContainer: Any, expression: ColumnExpression): Any?
}
