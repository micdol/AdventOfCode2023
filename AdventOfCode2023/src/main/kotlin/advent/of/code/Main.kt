package advent.of.code

import advent.of.code.day1.Day1Part1
import advent.of.code.day1.Day1Part2
import advent.of.code.day2.Day2Part1
import advent.of.code.day2.Day2Part2
import advent.of.code.day3.Day3Part1
import advent.of.code.day3.Day3Part2
import advent.of.code.day4.Day4Part1
import advent.of.code.day4.Day4Part2
import advent.of.code.day5.Day5Part1
import advent.of.code.day5.Day5Part2
import kotlin.time.DurationUnit
import kotlin.time.measureTimedValue

fun main(args: Array<String>) {
    val days = listOf(
        Day1Part1(),
        Day1Part2(),
        Day2Part1(),
        Day2Part2(),
        Day3Part1(),
        Day3Part2(),
        Day4Part1(),
        // Day4Part2(),
        Day5Part1(),
        Day5Part2(),
    )

    days.forEach {
        val res = measureTimedValue { it.solve() }
        println("[${it.javaClass.simpleName}] [${res.duration.toString(DurationUnit.SECONDS, 3)}] : ${res.value}")
    }
}
