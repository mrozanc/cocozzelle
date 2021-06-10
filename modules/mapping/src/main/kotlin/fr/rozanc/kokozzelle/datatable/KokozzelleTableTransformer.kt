package fr.rozanc.kokozzelle.datatable

import com.fasterxml.jackson.databind.ObjectMapper
import io.cucumber.datatable.DataTable
import io.cucumber.datatable.TableTransformer
import java.lang.reflect.Type

class KokozzelleTableTransformer(
    private val objectMapper: ObjectMapper
) {

    fun <T> transform(table: DataTable, toValueType: Type): T {
        TODO("Not yet implemented")
    }
}
