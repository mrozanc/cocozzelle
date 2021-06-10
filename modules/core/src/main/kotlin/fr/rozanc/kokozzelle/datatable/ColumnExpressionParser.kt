package fr.rozanc.kokozzelle.datatable

interface ColumnExpressionParser {
    fun parse(expression: String): ColumnExpression
}
