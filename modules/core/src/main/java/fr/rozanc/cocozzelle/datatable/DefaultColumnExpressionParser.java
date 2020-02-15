package fr.rozanc.cocozzelle.datatable;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DefaultColumnExpressionParser implements ColumnExpressionParser {

    private final Pattern modifierPattern = Pattern.compile("\\s*\\((([^),\\s]+)(?:,\\s*[^),\\s]+)*)\\)\\s*$");

    @Override
    public ColumnExpression parse(final String expression) {
        final Matcher matcher = modifierPattern.matcher(expression);
        final Map<String, String> options = new LinkedHashMap<>();
        if (matcher.find()) {
            final String allModifiersString = matcher.group(1);
            final String[] modifiersStrings = allModifiersString.split(",\\s*");
            for (final String modifiersString : modifiersStrings) {
                final String[] elements = modifiersString.split(":", 1);
                if (elements.length > 1) {
                    options.put(elements[0], elements[1]);
                } else {
                    options.put(elements[0], "true");
                }
            }
        }
        return new DefaultColumnExpression(expression,
                                           expression.replaceFirst(modifierPattern.pattern(), ""),
                                           options);
    }
}
