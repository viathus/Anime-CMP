import platform.Foundation.NSNumber
import platform.Foundation.NSNumberFormatter
import platform.Foundation.NSNumberFormatterDecimalStyle

actual class Formatter actual constructor() {
    actual fun format(number: Double, decimalPoint: Int): String {
        val formatter = NSNumberFormatter().apply {
            numberStyle = NSNumberFormatterDecimalStyle
            minimumFractionDigits = decimalPoint.toULong()
            maximumFractionDigits = decimalPoint.toULong()
        }

        val nsNumber = NSNumber(number)
        return formatter.stringFromNumber(nsNumber) ?: number.toString()
    }
}