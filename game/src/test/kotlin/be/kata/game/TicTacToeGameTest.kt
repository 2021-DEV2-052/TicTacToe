package be.kata.game

import org.junit.jupiter.api.Test


class TicTacToeGameTest {

    @Test
    fun gameStartsWithPlayer1() {
        val game = TicTacToeGame()
        assert(game.activePlayer == TicTacToeGame.Player.PLAYER_1)
    }

    @Test
    fun gameExposesAFieldWith9Squares() {
        val game = TicTacToeGame()
        assert(game.field.size == 9)
    }
}