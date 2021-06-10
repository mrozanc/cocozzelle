package fr.rozanc.cocozzelle.datatable

import fr.rozanc.kokozzelle.datatable.DefaultColumnExpression
import fr.rozanc.kokozzelle.datatable.DefaultColumnExpressionParser
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class DefaultColumnExpressionParserSpec : DescribeSpec({

    describe("Default column parser") {
        val parser = DefaultColumnExpressionParser()

        mapOf(
            "simpleAttribute" to DefaultColumnExpression("simpleAttribute", "simpleAttribute", emptyMap()),
            "   simpleAttributeWithSpaces   " to DefaultColumnExpression("   simpleAttributeWithSpaces   ", "simpleAttributeWithSpaces", emptyMap()),
            "value (withModifier)" to DefaultColumnExpression("value (withModifier)", "value", mapOf("withModifier" to "true")),
            "value (trim, upper)" to DefaultColumnExpression("value (trim, upper)", "value", mapOf("trim" to "true", "upper" to "true")),
        ).forEach { (expression, parsedExpression) ->
            it("should parse \"$expression\"") {
                parser.parse(expression) shouldBe parsedExpression
            }
        }
    }
})
