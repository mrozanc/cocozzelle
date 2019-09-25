package fr.rozanc.cocozzelle.datatable;

import lombok.val;

import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class DefaultColumnExpressionParser implements ColumnExpressionParser {

    private final Pattern modifierPattern = Pattern.compile("\\s*\\((([^),\\s]+)(?:,\\s*[^),\\s]+)*)\\)\\s*$");

    @Override
    public ColumnExpression parse(final String expression) {
        val matcher = modifierPattern.matcher(expression);
        val options = new LinkedHashMap<String, String>();
        if (matcher.find()) {
            val allModifiersString = matcher.group(1);
            val modifiersStrings = allModifiersString.split(",\\s*");
            for (final String modifiersString : modifiersStrings) {
                val elements = modifiersString.split(":", 1);
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
