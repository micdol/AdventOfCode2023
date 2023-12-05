package advent.of.code.day5

open class Day5Part2 : Day5Part1() {

    override fun initialSeeds() = inputLines()[0].substringAfter("seeds: ")
        .split(" ")
        .chunked(2)
        .map {
            val start = it[0].toLong()
            val end = start + it[1].toLong()
            (start..end)
        }.let { ranges ->
            sequence {
                ranges.forEachIndexed { idx, range ->
                    println("processing range $idx/${ranges.size}")
                    for (e in range) {
                        yield(e)
                    }
                }
            }
        }
}
