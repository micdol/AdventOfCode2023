package advent.of.code.day4

class Day4Part2 : Day4Part1() {

    override fun solve(): String {
        val cards: MutableList<Card> = inputLines().map { parseCard(it) }.toMutableList()
        val cardMap = cards.associateBy { it.id }

        var i = 0
        while (i < cards.size) {
            val card = cards[i++]
            val idsToAdd = (card.id + 1)..(card.id + card.winCount())
            for (idToAdd in idsToAdd) {
                cardMap[idToAdd]?.copy()?.let {
                    cards.add(it)
                }
            }
        }

        return cards.size.toString()
    }
}
