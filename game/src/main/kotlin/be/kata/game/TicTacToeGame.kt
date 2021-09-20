package be.kata.game

class TicTacToeGame {

    companion object {
        private const val FIELD_SIZE = 9
    }

    private val _field: Array<Player?> = arrayOfNulls(FIELD_SIZE)
    val field get() = _field.asList()

    private var _activePlayer: Player = Player.PLAYER_1
    val activePlayer get() = _activePlayer

    fun claimSquare(squareToClaim: Int) {
        _field[squareToClaim] = Player.PLAYER_1
    }

    enum class Player {
        PLAYER_1, PLAYER_2
    }
}