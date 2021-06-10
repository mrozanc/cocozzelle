package fr.rozanc.kokozzelle.datatable

import io.cucumber.datatable.DataTable
import java.util.stream.Collectors

class DataTableMatcher(
    private val objectViewFactory: ObjectViewFactory,
    private val columnExpressionParser: ColumnExpressionParser
) {

    fun match(data: Any?, expectedDataTable: DataTable): DataTableMatch? {
//        expectedDataTable.asList(SampleModel.class);
        if (expectedDataTable.cells().isEmpty()) {
            return null
        }
        //        expectedDataTable.asList(data.getClass());
        val objectView = objectViewFactory.createView(
            data,
            expectedDataTable.cells()[0]
                .stream()
                .map { expression: String? ->
                    columnExpressionParser.parse(
                        expression!!
                    )
                }
                .collect(Collectors.toList()))
        return DataTableMatch(false)
    }
}