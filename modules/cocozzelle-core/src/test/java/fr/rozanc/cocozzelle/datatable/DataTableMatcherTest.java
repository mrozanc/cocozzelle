package fr.rozanc.cocozzelle.datatable;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.rozanc.cocozzelle.datatable.sample.SampleData;
import io.cucumber.datatable.DataTable;
import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class DataTableMatcherTest {

    @Test
    void testMatch() {
        val data = SampleData.builder().id(12).name("Toto").value(3.14).build();
        val dataTable = DataTable.create(Arrays.asList(Arrays.asList("id (js)", "name"),
                                                       Arrays.asList("12", "Toto")));
        val matcher = new DataTableMatcher(new JacksonObjectViewFactory(new ObjectMapper()), new DefaultColumnExpressionParser());
        val result = matcher.match(data, dataTable);
        assertThat(result.matches()).as("match").isTrue();
    }
}