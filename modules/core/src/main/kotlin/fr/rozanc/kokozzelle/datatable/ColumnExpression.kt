package fr.rozanc.kokozzelle.datatable

interface ColumnExpression {
    val originalString: String
    val string: String
    val options: Map<String, String>
}
