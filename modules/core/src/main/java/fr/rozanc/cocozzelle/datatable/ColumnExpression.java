package fr.rozanc.cocozzelle.datatable;

import java.util.Map;

public interface ColumnExpression {

    String getOriginalString();

    String getString();

    Map<String, String> getOptions();
}
