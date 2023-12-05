package advent.of.code.day1

/**
 * Your calculation isn't quite right. It looks like some of the digits are actually spelled out with letters: one, two, three, four, five, six, seven, eight, and nine also count as valid "digits".
 *
 * Equipped with this new information, you now need to find the real first and last digit on each line. For example:
 *
 * two1nine
 * eightwothree
 * abcone2threexyz
 * xtwone3four
 * 4nineeightseven2
 * zoneight234
 * 7pqrstsixteen
 * In this example, the calibration values are 29, 83, 13, 24, 42, 14, and 76. Adding these together produces 281.
 */
class Day1Part2 : Day1Part1() {
    private val alphabet = mapOf(
        "zero" to "0",
        "one" to "1",
        "two" to "2",
        "three" to "3",
        "four" to "4",
        "five" to "5",
        "six" to "6",
        "seven" to "7",
        "eight" to "8",
        "nine" to "9",
        "ten" to "10",
        "eleven" to "11",
        "twelve" to "12",
        "thirteen" to "13",
        "fourteen" to "14",
        "fifteen" to "15",
        "sixteen" to "16",
        "seventeen" to "17",
        "eighteen" to "18",
        "nineteen" to "19",
        "twenty" to "20",
        "thirty" to "30",
        "forty" to "40",
        "fifty" to "50",
        "sixty" to "60",
        "seventy" to "70",
        "eighty" to "80",
        "ninety" to "90",
    )

    override fun solve(): String = inputLines()
        .sumOf { firstDigit(it) * 10 + lastDigit(it) }
        .toString()

    private fun firstDigit(line: String): Int {
        if (line.first().isDigit()) {
            return line.first().digitToInt()
        }

        val firstDigitIdx = line.indexOfFirst { it.isDigit() }.let { if (it < 0) line.length else it }
        return alphabet.keys.asSequence()
            .associateWith { line.indexOf(it, ignoreCase = true) }
            .filterValues { it in 0..<firstDigitIdx }
            .minByOrNull { it.value }
            ?.let { alphabet.getValue(it.key).first().digitToInt() }
            ?: line[firstDigitIdx].digitToInt()
    }

    private fun lastDigit(line: String): Int {
        if (line.last().isDigit()) {
            return line.last().digitToInt()
        }

        val lastDigitIdx = line.indexOfLast { it.isDigit() }
        return alphabet.keys.asSequence()
            .associateWith { line.lastIndexOf(it, ignoreCase = true) }
            .filterValues { it > lastDigitIdx }
            .maxByOrNull { it.value }
            ?.let { alphabet.getValue(it.key).last().digitToInt() }
            ?: line[lastDigitIdx].digitToInt()
    }
}
