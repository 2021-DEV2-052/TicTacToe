package be.kata.game

class TicTacToeGame {
    val field: Array<Player?> = arrayOfNulls(9)
    var activePlayer: Player = Player.PLAYER_1

    enum class Player {
        PLAYER_1, PLAYER_2
    }
}