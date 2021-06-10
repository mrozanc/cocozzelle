package fr.rozanc.kokozzelle.dataview.accessor

import fr.rozanc.kokozzelle.datatable.DefaultColumnExpression
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

object KeyValueAccessorSpec : DescribeSpec({
    describe("A key data accessor") {

        describe("accessing an object property") {
            val keyDataAccessor = KeyValueAccessor(DefaultColumnExpression("myProperty", "myProperty", emptyMap()))

            it("returns the given property of an object") {
                val obj = object {
                    val myProperty = "This is a simple String."
                }
                keyDataAccessor.accessValue(obj) shouldBe "This is a simple String."
            }
        }

        describe("accessing a map entry") {
            val keyDataAccessor = KeyValueAccessor(DefaultColumnExpression("myKey", "myKey", emptyMap()))

            it("returns the value at the given key") {
                val map = mapOf(
                    "myKey" to "my value",
                    "hello" to "world"
                )
                keyDataAccessor.accessValue(map) shouldBe "my value"
            }
        }
    }
})
