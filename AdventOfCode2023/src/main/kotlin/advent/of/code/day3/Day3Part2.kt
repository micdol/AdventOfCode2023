package advent.of.code.day3

import advent.of.code.DaySolver

/**
 * The engineer finds the missing part and installs it in the engine! As the engine springs to life, you jump in the closest gondola, finally ready to ascend to the water source.
 *
 * You don't seem to be going very fast, though. Maybe something is still wrong? Fortunately, the gondola has a phone labeled "help", so you pick it up and the engineer answers.
 *
 * Before you can explain the situation, she suggests that you look out the window. There stands the engineer, holding a phone in one hand and waving with the other. You're going so slowly that you haven't even left the station. You exit the gondola.
 *
 * The missing part wasn't the only issue - one of the gears in the engine is wrong. A gear is any * symbol that is adjacent to exactly two part numbers. Its gear ratio is the result of multiplying those two numbers together.
 *
 * This time, you need to find the gear ratio of every gear and add them all up so that the engineer can figure out which gear needs to be replaced.
 *
 * Consider the same engine schematic again:
 *
 * 467..114..
 * ...*......
 * ..35..633.
 * ......#...
 * 617*......
 * .....+.58.
 * ..592.....
 * ......755.
 * ...$.*....
 * .664.598..
 * In this schematic, there are two gears. The first is in the top left; it has part numbers 467 and 35, so its gear ratio is 16345. The second gear is in the lower right; its gear ratio is 451490. (The * adjacent to 617 is not a gear because it is only adjacent to one part number.) Adding up all of the gear ratios produces 467835.
 */
open class Day3Part2 : DaySolver {

    val gearRegex = Regex("\\*")
    val numberRegex = Regex("[0-9]+")

    override fun solve(): String {
        // row by row
        val board = inputText().split("\n")

        // list[row -> range]
        val gearsMap: Map<Int, MutableSet<IntRange>> = board.indices.associateWith { mutableSetOf() }

        // all gears
        board.forEachIndexed { row, line ->
            val symbolMatches = gearRegex.findAll(line)
            symbolMatches.map { maxOf(0, it.range.first - 1)..minOf(it.range.last + 1, line.length) }.forEach {
                gearsMap[row]?.add(it)
            }
        }

        return gearsMap.map { e ->
            val row = e.key
            val gears = e.value

            gears.sumOf { gear ->
                val nums = numberRegex.findAll(board[row])
                    .filter { it.range.intersects(gear) }
                    .map { it.value.toLong() }
                    .toMutableList()
                if (row > 0) {
                    nums.addAll(
                        numberRegex.findAll(board[row - 1])
                            .filter { it.range.intersects(gear) }
                            .map { it.value.toLong() }
                            .toMutableList(),
                    )
                }
                if (row < board.size) {
                    nums.addAll(
                        numberRegex.findAll(board[row + 1])
                            .filter { it.range.intersects(gear) }
                            .map { it.value.toLong() }
                            .toMutableList(),
                    )
                }
                if (nums.size == 2) {
                    nums.reduce(Long::times)
                } else {
                    0
                }
            }
        }.sum().toString()
    }

    private fun IntRange.intersects(r: IntRange) = intersect(r).isNotEmpty()
}
