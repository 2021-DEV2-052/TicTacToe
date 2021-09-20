package be.kata.game

class TicTacToeGame {

    companion object {
        const val FIELD_SIZE = 9
        private val WIN_COMBOS = setOf(
            setOf(0, 1, 2), setOf(3, 4, 5), setOf(6, 7, 8), // horizontal
            setOf(0, 3, 6), setOf(1, 4, 7), setOf(2, 5, 8), // vertical
            setOf(0, 4, 8), setOf(2, 4, 6) // diagonal
        )
    }

    private val gameField: Array<TicTacToeSquare> = Array(FIELD_SIZE) { Nothing }

    private var activePlayer: Player = Player.PLAYER_1

    private var status: Status = Playing

    val state: State get() = State(gameField.toList(), activePlayer, status)

    fun playTurn(squareOrdinalToClaim: Int): State {
        checkIfPlayingIsAllowed()
        claimSquare(squareOrdinalToClaim)
        alterStatusIfNeeded()
        switchPlayer()
        return state
    }

    private fun checkIfPlayingIsAllowed() {
        if (status.ended) {
            throw GameHasEndedException("Game has ended, you cannot play anymore")
        }
    }

    private fun claimSquare(squareOrdinal: Int) {
        gameField[squareOrdinal] = gameField[squareOrdinal].claim(activePlayer)
    }

    private fun switchPlayer() {
        activePlayer = Player.values().first { it != activePlayer }
    }

    private fun alterStatusIfNeeded() {
        val winComboMatches = winComboMatches()
        if (allSquaresClaimed() || winComboMatches) {
            status = if (winComboMatches) {
                Winner(activePlayer)
            } else {
                Draw
            }
        }
    }

    private fun winComboMatches() =
        WIN_COMBOS
            .any { winCombo ->
                winCombo.map { squareOrdinal -> gameField[squareOrdinal] }
                    .all { square -> square is Claimed && square.player == activePlayer }
            }

    private fun allSquaresClaimed() = gameField.all { square -> square is Claimed }

    enum class Player {
        PLAYER_1, PLAYER_2
    }

    data class State(
        val field: List<TicTacToeSquare>,
        val activePlayer: Player,
        val status: Status
    )

    sealed class Status {
        abstract val ended: Boolean
    }

    object Playing : Status() {
        override val ended: Boolean = false
    }

    abstract class Ended : Status() {
        override val ended: Boolean = true
    }

    data class Winner(val winningPlayer: Player) : Ended()

    object Draw : Ended()
}