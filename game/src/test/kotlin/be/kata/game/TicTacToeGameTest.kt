package be.kata.game

import org.junit.jupiter.api.Test


class TicTacToeGameTest {

    @Test
    fun gameStartsWithPlayer1() {
        val game = TicTacToeGame()
        assert(game.activePlayer == TicTacToeGame.Player.PLAYER_1)
    }
}