package fr.rozanc.kokozzelle.dataview.accessor

import fr.rozanc.kokozzelle.datatable.DefaultColumnExpression
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

object JsValueAccessorSpec : DescribeSpec({
    describe("A JavaScript data accessor") {

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
                val jsDataAccessor = JsValueAccessor(
                    DefaultColumnExpression(
                        "answerToLifeTheUniverseAndEverything",
                        "answerToLifeTheUniverseAndEverything", emptyMap()
                    )
                )

                jsDataAccessor.accessValue(obj) shouldBe 42
            }

            it("returns a value in an array") {
                val jsDataAccessor = JsValueAccessor(
                    DefaultColumnExpression("anArray[1]", "anArray[1]", emptyMap())
                )

                jsDataAccessor.accessValue(obj) shouldBe 'b'
            }

            it("returns a value in a map or an object") {
                val jsDataAccessor = JsValueAccessor(
                    DefaultColumnExpression("aMap['hello'].name", "aMap['hello'].name", emptyMap())
                )

                jsDataAccessor.accessValue(obj) shouldBe "Obiwan Kenobi"
            }
        }
    }
})
