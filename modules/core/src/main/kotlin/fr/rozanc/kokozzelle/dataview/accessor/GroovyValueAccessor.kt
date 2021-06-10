package fr.rozanc.kokozzelle.dataview.accessor

import fr.rozanc.kokozzelle.datatable.ColumnExpression
import groovy.lang.GroovyShell
import groovy.lang.Script

class GroovyValueAccessor(private val expression: ColumnExpression) : ValueAccessor {

    companion object {
        val SHELL = GroovyShell()
    }

    private val script: Script

    init {
        val scriptText = """
            def accessor(Object obj) {
                def internalAccessor = {
                    return ${expression.string}
                }
                internalAccessor.delegate = obj
                internalAccessor()
            }
        """.trimIndent()
        script = SHELL.parse(scriptText)
    }

    override fun accessValue(dataContainer: Any): Any? {
        return script.invokeMethod("accessor", dataContainer)
    }
}
