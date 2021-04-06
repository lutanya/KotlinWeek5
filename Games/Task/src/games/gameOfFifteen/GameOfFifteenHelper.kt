package games.gameOfFifteen

/*
 * This function should return the parity of the permutation.
 * true - the permutation is even
 * false - the permutation is odd
 * https://en.wikipedia.org/wiki/Parity_of_a_permutation

 * If the game of fifteen is started with the wrong parity, you can't get the correct result
 *   (numbers sorted in the right order, empty cell at last).
 * Thus the initial permutation should be correct.
 */
fun isEven(permutation: List<Int>): Boolean {
    var rightPosition = listOf<Int>()
    rightPosition = if (permutation.contains(0)) {
        (permutation.indices).toList()
    } else {
        (1..permutation.size).toList()
    }
    var permutations = permutation.zip(rightPosition).filter { (first, second) -> first != second }
    if (permutations.isEmpty() || permutations[0].first == 0) return true
    var countOfPermutations = 0
    while (permutations.isNotEmpty()) {
        val newPermutation = mutableListOf<Int>()
        var startElement = permutations[0].first
        newPermutation.add(startElement)
        var endElement = permutations[0].second
        permutations = permutations.filterIndexed { index, _ -> index != 0 }
        while (permutations.isNotEmpty() && startElement != endElement) {
            newPermutation.add(endElement)
            var nextPermutation = permutations.find { (first, _) -> first == endElement }
            endElement = nextPermutation?.second!!
            permutations = permutations.filter { it != nextPermutation }
        }
        countOfPermutations += newPermutation.size - 1
    }
    return countOfPermutations % 2 == 0
}