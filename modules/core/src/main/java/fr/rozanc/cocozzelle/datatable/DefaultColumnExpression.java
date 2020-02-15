package fr.rozanc.cocozzelle.datatable;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class DefaultColumnExpression implements ColumnExpression {

    private final String originalString;

    private final String expressionString;

    private final Map<String, String> options;

    public DefaultColumnExpression(final String originalExpression,
                                   final String expression,
                                   final Map<String, String> options) {
        this.originalString = originalExpression;
        this.expressionString = expression;
        this.options = new LinkedHashMap<>(options);
    }

    @Override
    public String getOriginalString() {
        return originalString;
    }

    @Override
    public String getString() {
        return expressionString;
    }

    @Override
    public Map<String, String> getOptions() {
        return Collections.unmodifiableMap(options);
    }
}
