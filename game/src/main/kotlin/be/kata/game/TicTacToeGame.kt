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

    private var ended: Boolean = false

    private var winner: Player? = null

    val state: State get() = State(gameField.toList(), activePlayer, ended, winner)

    fun playTurn(squareOrdinalToClaim: Int): State {
        claimSquare(squareOrdinalToClaim)
        checkForEndCondition()
        checkForWinner()
        switchPlayer()
        return state
    }

    private fun claimSquare(squareOrdinal: Int) {
        gameField[squareOrdinal] = gameField[squareOrdinal].claim(activePlayer)
    }

    private fun switchPlayer() {
        activePlayer = Player.values().first { it != activePlayer }
    }

    private fun checkForEndCondition() {
        if (allSquaresClaimed() || winComboMatches()) {
            ended = true
        }
    }

    private fun checkForWinner() {
        if (ended && winComboMatches()) {
            winner = activePlayer
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
        val ended: Boolean,
        val winner: Player?
    )
}