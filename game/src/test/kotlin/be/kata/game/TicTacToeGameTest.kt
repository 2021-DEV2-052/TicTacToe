package be.kata.game

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals


class TicTacToeGameTest {

    private lateinit var game: TicTacToeGame

    @BeforeEach
    fun setup() {
        game = TicTacToeGame()
    }

    @Test
    fun gameStartsWithPlayer1() {
        assertEquals(
            TicTacToeGame.Player.PLAYER_1,
            game.activePlayer,
            "A game always starts with player 1"
        )
    }

    @Test
    fun gameExposesAFieldWith9Squares() {
        assertEquals(9, game.field.size, "Field should contain 9 squares")
    }

    @Test
    fun playersCanClaimSquare() {
        val squareToClaim = (0 until TicTacToeGame.FIELD_SIZE).random()
        game.claimSquare(squareToClaim)
        assertEquals(
            TicTacToeGame.Player.PLAYER_1,
            (game.field[squareToClaim] as Claimed).player,
            "Field should be claimed by player 1"
        )
    }

    @Test
    fun playersCannotClaimAClaimedSquare() {
        val squareToClaim = (0 until TicTacToeGame.FIELD_SIZE).random()
        game.claimSquare(squareToClaim)
        assertThrows<IllegalArgumentException>("Claiming a square twice should throw Exception") {
            game.claimSquare(squareToClaim)
        }
    }
}