package fr.rozanc.kokozzelle.dataview.accessor

/**
 * Defines a way to retrieve a value from a given object.
 */
interface ValueAccessor {

    fun accessValue(dataContainer: Any): Any?
}
