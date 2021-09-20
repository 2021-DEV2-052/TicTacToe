package be.kata.game

import org.junit.jupiter.api.Test
import kotlin.random.Random
import kotlin.test.assertEquals


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

    @Test
    fun playersCanClaimSquare() {
        val game = TicTacToeGame()
        val squareToClaim = (0 until 9).random()
        game.claimSquare(squareToClaim)
        assertEquals(TicTacToeGame.Player.PLAYER_1, game.field[squareToClaim])
    }
}