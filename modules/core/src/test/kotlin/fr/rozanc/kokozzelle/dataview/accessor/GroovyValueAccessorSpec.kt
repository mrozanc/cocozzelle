package fr.rozanc.kokozzelle.dataview.accessor

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.convertValue
import fr.rozanc.kokozzelle.datatable.DefaultColumnExpression
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.startWith

class GroovyValueAccessorSpec : DescribeSpec({
    describe("A Groovy data accessor") {

        describe("accessing an object property") {
            data class ExampleData(val id: Int, val name: String, val value: Double)

            val obj =
                object {
                    val answerToLifeTheUniverseAndEverything = 42
                    val anArray = arrayOf('a', 'b', 'c')

                    @JvmField
                    val aMap = mapOf(
                        "hello" to ExampleData(10, "Obiwan Kenobi", 9.0),
                        "world" to ExampleData(11, "Jar Jar Binks", 1.0)
                    )
                }

            it("returns the given property of an object") {
                val groovyDataAccessor = GroovyValueAccessor(
                    DefaultColumnExpression(
                        "answerToLifeTheUniverseAndEverything",
                        "answerToLifeTheUniverseAndEverything", emptyMap()
                    )
                )

                groovyDataAccessor.accessValue(obj) shouldBe 42
            }

            it("returns a value in an array") {
                val groovyDataAccessor = GroovyValueAccessor(
                    DefaultColumnExpression("anArray[1]", "anArray[1]", emptyMap())
                )

                groovyDataAccessor.accessValue(obj) shouldBe 'b'
            }

            it("returns a value in a map or an object") {
                val groovyDataAccessor = GroovyValueAccessor(
                    DefaultColumnExpression("aMap['hello'].name", "aMap['hello'].name", emptyMap())
                )

                groovyDataAccessor.accessValue(obj) shouldBe "Obiwan Kenobi"
            }

            it("is funny") {
                val objectMapper = ObjectMapper().apply {
                    registerModule(KotlinModule())
                }
                val objAsMap = objectMapper.convertValue<Map<String, Any?>>(obj)
                val type = objectMapper.typeFactory.constructType(objAsMap::class.java)
                val expr = "aMap['moto'].name"
                val exprPieces = expr.split(Regex("(?=[\\[.])")).map { it.trim() }
                var previousValue = objAsMap
                for (i in exprPieces.indices) {
                    val currentExpr = exprPieces.subList(0, i).joinToString("")
                    val currentValue = GroovyValueAccessor(DefaultColumnExpression(currentExpr, currentExpr, emptyMap())).accessValue(objAsMap)
                    if (currentValue == null && i < exprPieces.size - 1) {
//                        when (exprPieces[i + 1]) {
//                            startsWith("['"), startsWith("[\"") -> previousValue[]
//                        }
                    }
                }
            }
        }
    }
})
