package fr.rozanc.kokozzele.mapping.input

import com.fasterxml.jackson.dataformat.javaprop.JavaPropsMapper
import com.fasterxml.jackson.dataformat.javaprop.JavaPropsSchema
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import io.kotest.core.spec.style.DescribeSpec
import java.util.*


class InputSpec : DescribeSpec({
    describe("Jackson serialization to properties") {
        it("should serialize an object to properties") {
            data class Project(val name: String, val language: String, val plop: Map<String, List<Double>>)

            val data = Project(
                "kotlinx.serialization", "Kotlin", mapOf(
                    "a.g" to listOf(12.3, 4.5),
                    "b" to listOf(-5.4, 7.0, 0.0),
                    "hop" to emptyList()
                )
            )
            val schema = JavaPropsSchema.emptySchema()
                .withWriteIndexUsingMarkers(true)
                .withFirstArrayOffset(0)

            val mapper = JavaPropsMapper().apply {
                registerKotlinModule()
            }

            println(mapper.writer(schema).writeValueAsString(data))
            val props = Properties().apply {
                load("""name=kotlinx.serialization
language=Kotlin
plop['a'][1]=12.3
plop['a'][2]=4.5
plop['b'][1]=-5.4
plop['b'][2]=7.0
plop['b'][4]=5.0
plop['b'][3]=3.14""".byteInputStream())
            }

            val normalizedProps = Properties().apply {
                putAll(props.mapKeys { (it.key as String).replace(Regex("\\[([\"']?)([^]]+)\\1]"), ".\$2") })
            }

            println(mapper.readPropertiesAs(normalizedProps, Project::class.java))
        }
    }
})
