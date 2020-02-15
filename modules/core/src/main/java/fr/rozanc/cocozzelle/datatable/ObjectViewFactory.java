package fr.rozanc.cocozzelle.datatable;

import java.util.List;

public interface ObjectViewFactory {

    DataTableView createView(Object object, List<ColumnExpression> columnExpressions);
}
