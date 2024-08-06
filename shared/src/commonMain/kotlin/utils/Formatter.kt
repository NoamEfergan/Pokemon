package utils

object Formatter {
    fun padToThreeDigits(number: Int): String {
        require(number in 1..999) { "Number must be between 1 and 999" }
        return number.toString().padStart(3, '0')
    }
}