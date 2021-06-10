package fr.rozanc.kokozzelle.datatable

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.convertValue
import fr.rozanc.kokozzelle.dataview.DataTableRowView

class JacksonObjectViewFactory internal constructor(private val objectMapper: ObjectMapper) : ObjectViewFactory {

    override fun createView(obj: Any?, columnExpressions: List<ColumnExpression>): DataTableRowView {
        val objectAsMap: Map<String, Any?> = obj?.let { objectMapper.convertValue(it) } ?: emptyMap()
        return DataTableRowView(objectAsMap, )
    }
}
