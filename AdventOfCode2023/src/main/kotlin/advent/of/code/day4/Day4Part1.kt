package advent.of.code.day4

import advent.of.code.DaySolver
import kotlin.math.pow

open class Day4Part1 : DaySolver {

    data class Card(val id: Long, val winnings: Set<Long>, val picks: Set<Long>) {
        fun winCount() = picks.intersect(winnings).count()
        fun value() = 2.0.pow(winCount() - 1).toLong()
    }

    override fun solve() =
        inputLines()
            .map { parseCard(it) }
            .sumOf { it.value() }
            .toString()

    fun parseCard(line: String): Card {
        val id = line.substringBefore(":").substringAfter("Card").trim().toLong()
        val numbers = line.substringAfter(":").split("|")
        val winnings = numbers[0].trim().split("\\s+".toRegex()).map { it.trim().toLong() }.toSet()
        val picks = numbers[1].trim().split("\\s+".toRegex()).map { it.trim().toLong() }.toSet()
        return Card(id, winnings, picks)
    }
}
