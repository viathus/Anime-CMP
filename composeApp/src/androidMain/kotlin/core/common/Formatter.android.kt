import java.text.NumberFormat

actual class Formatter actual constructor() {
    actual fun format(number: Double, decimalPoint: Int): String {
        val formatter = NumberFormat.getNumberInstance().apply {
            minimumFractionDigits = decimalPoint
            maximumFractionDigits = decimalPoint
        }

        return formatter.format(number)
    }
}