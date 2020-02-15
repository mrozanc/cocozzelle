package fr.rozanc.kokozzelle.dataview.accessor

import fr.rozanc.cocozzelle.datatable.ColumnExpression
import javax.script.ScriptContext
import javax.script.ScriptEngine
import javax.script.ScriptEngineManager

class JsValueAccessor : ValueAccessor {

    private val engine: ScriptEngine = ScriptEngineManager().getEngineByName("nashorn")

    override fun accessValue(dataContainer: Any, expression: ColumnExpression): Any? {
        engine.context.setAttribute("obj", dataContainer, ScriptContext.ENGINE_SCOPE)
        return engine.eval("obj.${expression.string}")
    }
}
