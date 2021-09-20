package be.kata.game

class TicTacToeGame {

    companion object {
        const val FIELD_SIZE = 9
        private val WIN_COMBO = listOf(0, 1, 2)
    }

    private val gameField: Array<TicTacToeSquare> = Array(FIELD_SIZE) { Nothing }

    private var activePlayer: Player = Player.PLAYER_1

    private var ended: Boolean = false

    val state: State get() = State(gameField.toList(), activePlayer, ended)

    fun playTurn(squareOrdinalToClaim: Int): State {
        claimSquare(squareOrdinalToClaim)
        checkForEndCondition()
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
        if (allSquaresClaimed() || findWinComboMatch()) {
            ended = true
        }
    }

    private fun findWinComboMatch() =
        WIN_COMBO
            .map { squareOrdinal -> gameField[squareOrdinal] }
            .all { square -> square is Claimed && square.player == activePlayer }

    private fun allSquaresClaimed() = gameField.all { square -> square is Claimed }

    enum class Player {
        PLAYER_1, PLAYER_2
    }

    data class State(val field: List<TicTacToeSquare>, val activePlayer: Player, val ended: Boolean, val winner: Player? = null)
}