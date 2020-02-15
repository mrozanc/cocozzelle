package fr.rozanc.kokozzelle.dataview.transformer

import com.fasterxml.jackson.databind.ObjectMapper

class JsonValueTransformer<T : Any>(private val objectMapper: ObjectMapper) : ValueTransformer<String, T> {

    override fun transformValue(value: String?, clazz: Class<T>): T? {
        if (value == null || value.isEmpty()) {
            return null
        }
        return objectMapper.convertValue(value, clazz)
    }
}
