package fr.rozanc.kokozzelle.datatable

import fr.rozanc.kokozzelle.dataview.DataTableRowView

interface ObjectViewFactory {
    fun createView(obj: Any?, columnExpressions: List<ColumnExpression>): DataTableRowView
}
