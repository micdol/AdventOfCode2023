package advent.of.code.day5

import advent.of.code.DaySolver

open class Day5Part1 : DaySolver {

    class RangeMap(val name: String) {
        private val ranges: MutableList<Pair<LongRange, LongRange>> = mutableListOf()

        fun add(pair: Pair<LongRange, LongRange>) {
            add(pair.first, pair.second)
        }

        fun add(source: LongRange, target: LongRange) {
            ranges.add(source to target)
        }

        fun get(key: Long) =
            ranges.find { it.first.contains(key) }
                ?.let {
                    val (source, target) = it
                    val offset = key - source.first
                    target.first + offset
                }
                ?: key

        override fun toString(): String {
            return "RangeMap"
        }
    }

    override fun solve(): String {
        val lines = inputLines()
        val seeds = initialSeeds()
        val maps = listOf(
            "seed-to-soil",
            "soil-to-fertilizer",
            "fertilizer-to-water",
            "water-to-light",
            "light-to-temperature",
            "temperature-to-humidity",
            "humidity-to-location",
        ).map { lines.sublistForMap(it).parseRangeMap(it) }

        return seeds.minOfOrNull { seed ->
            var i = seed
            maps.forEach { map -> i = map.get(i) }
            i
        }.toString()
    }

    open fun initialSeeds() = inputLines()[0].substringAfter("seeds: ")
        .split(" ")
        .map { it.toLong() }
        .asSequence()

    private fun List<String>.sublistForMap(name: String) = run {
        val start = indexOf("$name map:")
        drop(start + 1).takeWhile { it.isNotBlank() }
    }

    private fun List<String>.parseRangeMap(name: String) = RangeMap(name)
        .apply {
            this@parseRangeMap
                .map { it.split(" ") }
                .map {
                    val target = it[0].toLong()
                    val source = it[1].toLong()
                    val length = it[2].toLong()
                    (source..(source + length)) to (target..(target + length))
                }
                .forEach(this::add)
        }
}
