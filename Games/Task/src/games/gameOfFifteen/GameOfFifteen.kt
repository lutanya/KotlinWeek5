package games.gameOfFifteen

import board.*
import games.game.Game
import games.game2048.deepEquals
import games.game2048.moveValues
import games.game2048.moveValuesInRowOrColumn

/*direction
 * Implement the Game of Fifteen (https://en.wikipedia.org/wiki/15_puzzle).
 * When you finish, you can play the game by executing 'PlayGameOfFifteen'.
 */
fun newGameOfFifteen(initializer: GameOfFifteenInitializer = RandomGameInitializer()): Game = object : Game {
    val initialPermutation: List<Int> = initializer.initialPermutation
    private val board = createGameBoard<Int?>(4)

    override fun initialize() {
        board.getAllCells().mapIndexed { index, cell ->
            if (index < board.getAllCells().size - 1) board[cell] = initialPermutation[index]
        }
    }

    override fun canMove(): Boolean {

        return true
    }

    override fun hasWon(): Boolean {
        return initialPermutation.deepEquals((1..initialPermutation.size).toList())
    }

    override fun processMove(direction: Direction) {
        var emptyCell: Cell? = board.find { it == null }
        with(board) {
            emptyCell?.getNeighbour(direction.reversed())
            var movePosition: Cell = emptyCell?.getNeighbour(direction.reversed()) ?: return
            this[emptyCell] = this[movePosition]
            this[movePosition] = null
        }
    }

    override operator fun get(i: Int, j: Int): Int? {
        return board[Cell(i, j)]
    }
}


