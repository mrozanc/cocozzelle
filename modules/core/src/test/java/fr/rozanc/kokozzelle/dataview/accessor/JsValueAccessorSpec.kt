package fr.rozanc.kokozzelle.dataview.accessor

import assertk.assertThat
import assertk.assertions.isEqualTo
import fr.rozanc.cocozzelle.datatable.DefaultColumnExpression
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object JsValueAccessorSpec : Spek({
    describe("A JavaScript data accessor") {
        val jsDataAccessor by memoized { JsValueAccessor() }

        describe("accessing an object property") {
            val obj by memoized {
                data class ExampleData(val id: Int, val name: String, val value: Double)
                object {
                    val answerToLifeTheUniverseAndEverything = 42
                    val anArray = arrayOf('a', 'b', 'c')
                    @JvmField
                    val aMap = mapOf(
                        "hello" to ExampleData(10, "Obiwan Kenobi", 9.0),
                        "world" to ExampleData(11, "Jar Jar Binks", 1.0)
                    )
                }
            }

            it("returns the given property of an object") {
                assertThat(
                    jsDataAccessor.accessValue(
                        obj,
                        DefaultColumnExpression(
                            "answerToLifeTheUniverseAndEverything",
                            "answerToLifeTheUniverseAndEverything", emptyMap()
                        )
                    )
                ).isEqualTo(42)
            }

            it("returns a value in an array") {
                assertThat(jsDataAccessor.accessValue(
                    obj,
                    DefaultColumnExpression("anArray[1]", "anArray[1]", emptyMap())
                )).isEqualTo('b')
            }

            it("returns a value in a map or an object") {
                assertThat(jsDataAccessor.accessValue(
                    obj,
                    DefaultColumnExpression("aMap['hello'].name", "aMap['hello'].name", emptyMap())
                )).isEqualTo("Obiwan Kenobi")
            }
        }
    }
})
