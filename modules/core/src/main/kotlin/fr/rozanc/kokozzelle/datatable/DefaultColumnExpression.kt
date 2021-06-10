package fr.rozanc.kokozzelle.datatable

class DefaultColumnExpression(
    override val originalString: String,
    override val string: String,
    override val options: Map<String, String>
) : ColumnExpression {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DefaultColumnExpression

        if (originalString != other.originalString) return false
        if (string != other.string) return false
        if (options != other.options) return false

        return true
    }

    override fun hashCode(): Int {
        var result = originalString.hashCode()
        result = 31 * result + string.hashCode()
        result = 31 * result + options.hashCode()
        return result
    }

    override fun toString(): String {
        return "DefaultColumnExpression(originalString='$originalString', expressionString='$string', options=$options)"
    }
}
