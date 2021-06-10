package fr.rozanc.kokozzelle.datatable

import java.util.LinkedHashMap

class DefaultColumnExpressionParser : ColumnExpressionParser {

    companion object {
        private val MODIFIERS_PATTERN = Regex("\\s*\\((([^),\\s]+)(?:,\\s*[^),\\s]+)*)\\)\\s*$")
        private val MODIFIERS_SEPARATOR_PATTERN = Regex(",\\s*")
    }

    override fun parse(expression: String): ColumnExpression {
        val matcher = MODIFIERS_PATTERN.find(expression)
        val options: MutableMap<String, String> = LinkedHashMap()
        if (matcher != null) {
            val allModifiersString = matcher.groups[1]!!.value
            val modifiersStrings = allModifiersString.split(MODIFIERS_SEPARATOR_PATTERN).toTypedArray()
            for (modifiersString in modifiersStrings) {
                val elements = modifiersString.split(":", limit = 1).toTypedArray()
                if (elements.size > 1) {
                    options[elements[0]] = elements[1]
                } else {
                    options[elements[0]] = "true"
                }
            }
        }
        return DefaultColumnExpression(
            expression,
            expression.replaceFirst(MODIFIERS_PATTERN, "").trim(),
            options
        )
    }


}
