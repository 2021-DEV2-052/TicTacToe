package be.kata.game

class TicTacToeGame {

    companion object {
        const val FIELD_SIZE = 9
    }

    val ended: Boolean get() = false
    private val _field: Array<TicTacToeSquare> = Array(9) { Nothing }
    val field get() = _field.toList()

    private var _activePlayer: Player = Player.PLAYER_1
    val activePlayer get() = _activePlayer

    fun claimSquare(squareOrdinal: Int) {
        _field[squareOrdinal] = _field[squareOrdinal].claim(activePlayer)
        _activePlayer = Player.values().first { it != activePlayer}
    }

    enum class Player {
        PLAYER_1, PLAYER_2
    }
}