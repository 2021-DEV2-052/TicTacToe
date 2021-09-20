package be.kata.game

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class TicTacToeGameTest {

    private lateinit var game: TicTacToeGame

    @BeforeEach
    fun setup() {
        game = TicTacToeGame()
    }

    @Test
    fun gameStartsWithPlayer1() {
        assert(game.activePlayer == TicTacToeGame.Player.PLAYER_1)
    }

    @Test
    fun gameExposesAFieldWith9Squares() {
        assert(game.field.size == 9)
    }

    @Test
    fun playersCanClaimSquare() {
        val squareToClaim = (0 until TicTacToeGame.FIELD_SIZE).random()
        game.claimSquare(squareToClaim)
        assertEquals(TicTacToeGame.Player.PLAYER_1, game.field[squareToClaim])
    }
}