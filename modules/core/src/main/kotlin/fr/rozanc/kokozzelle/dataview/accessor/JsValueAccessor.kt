package fr.rozanc.kokozzelle.dataview.accessor

import fr.rozanc.kokozzelle.datatable.ColumnExpression
import javax.script.ScriptContext
import javax.script.ScriptEngine
import javax.script.ScriptEngineManager

class JsValueAccessor(private val expression: ColumnExpression) : ValueAccessor {

    private val engine: ScriptEngine = ScriptEngineManager().getEngineByName("nashorn")

    override fun accessValue(dataContainer: Any): Any? {
        engine.context.setAttribute("obj", dataContainer, ScriptContext.ENGINE_SCOPE)
        return engine.eval("obj.${expression.string}")
    }
}
