package fr.rozanc.kokozzelle.dataview.accessor

import assertk.assertThat
import assertk.assertions.isEqualTo
import fr.rozanc.cocozzelle.datatable.DefaultColumnExpression
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object KeyValueAccessorSpec : Spek({
    describe("A key data accessor") {
        val keyDataAccessor by memoized { KeyValueAccessor() }

        describe("accessing an object property") {
            it("returns the given property of an object") {
                val obj = object {
                    val myProperty = "This is a simple String."
                }
                assertThat(
                    keyDataAccessor.accessValue(obj, DefaultColumnExpression("myProperty", "myProperty", emptyMap()))
                ).isEqualTo("This is a simple String.")
            }
        }

        describe("accessing a map entry") {
            it("returns the value at the given key") {
                val map = mapOf(
                    "myKey" to "my value",
                    "hello" to "world"
                )
                assertThat(keyDataAccessor.accessValue(map, DefaultColumnExpression("myKey", "myKey", emptyMap())))
                    .isEqualTo("my value")
            }
        }
    }
})
