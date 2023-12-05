package advent.of.code

import java.io.FileNotFoundException

interface DaySolver {
    fun solve(): String

    fun inputReader() = javaClass.getResourceAsStream("input")?.bufferedReader()
        ?: throw FileNotFoundException("Couldn't find file 'input' in resources for ${javaClass.canonicalName}")

    fun inputLines() = inputReader().readLines()
    fun inputText() = inputReader().readText()
}
