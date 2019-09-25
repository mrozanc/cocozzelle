package fr.rozanc.cocozzelle.datatable;

import io.cucumber.datatable.DataTable;
import lombok.val;

import java.awt.image.SampleModel;
import java.util.stream.Collectors;

public class DataTableMatcher {

    private final ObjectViewFactory objectViewFactory;

    private final ColumnExpressionParser columnExpressionParser;

    public DataTableMatcher(final ObjectViewFactory objectViewFactory,
                            final ColumnExpressionParser columnExpressionParser) {
        this.objectViewFactory = objectViewFactory;
        this.columnExpressionParser = columnExpressionParser;
    }

    public DataTableMatch match(final Object data, final DataTable expectedDataTable) {
//        expectedDataTable.asList(SampleModel.class);
        if (expectedDataTable.cells().isEmpty()) {
            return null;
        }
//        expectedDataTable.asList(data.getClass());
        val objectView = objectViewFactory.createView(
                data,
                expectedDataTable.cells().get(0)
                                 .stream()
                                 .map(columnExpressionParser::parse)
                                 .collect(Collectors.toList()));
        return DataTableMatch.builder().matches(false).build();
    }
}
