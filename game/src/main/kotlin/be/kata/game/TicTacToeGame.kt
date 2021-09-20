package be.kata.game

class TicTacToeGame {

    companion object {
        const val FIELD_SIZE = 9
        private val WIN_COMBO = listOf(0, 1, 2)
    }

    private val _field: Array<TicTacToeSquare> = Array(9) { Nothing }
    val field get() = _field.toList()

    private var _activePlayer: Player = Player.PLAYER_1
    val activePlayer get() = _activePlayer

    private var _ended: Boolean = false
    val ended: Boolean get() = _ended

    fun playTurn(squareOrdinalToClaim: Int) {
        claimSquare(squareOrdinalToClaim)
        checkForEndCondition()
        switchPlayer()
    }

    private fun claimSquare(squareOrdinal: Int) {
        _field[squareOrdinal] = _field[squareOrdinal].claim(activePlayer)
    }

    private fun checkForEndCondition() {
        if (field.all { it is Claimed } || WIN_COMBO.map { _field[it] }.all { it is Claimed && it.player == activePlayer }) {
            _ended = true
        }
    }

    private fun switchPlayer() {
        _activePlayer = Player.values().first { it != activePlayer }
    }

    enum class Player {
        PLAYER_1, PLAYER_2
    }
}