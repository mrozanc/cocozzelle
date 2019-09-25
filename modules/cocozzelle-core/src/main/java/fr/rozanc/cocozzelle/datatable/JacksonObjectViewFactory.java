package fr.rozanc.cocozzelle.datatable;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.val;

import java.util.List;
import java.util.Map;

public class JacksonObjectViewFactory implements ObjectViewFactory {

    private final ObjectMapper objectMapper;

    JacksonObjectViewFactory(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public DataTableView createView(final Object object, final List<ColumnExpression> columnExpressions) {
        val objectAsMap = objectMapper.convertValue(object, Map.class);
        return null;
    }
}
