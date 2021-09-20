package be.kata.game

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertTrue


class TicTacToeGameTest {

    private lateinit var game: TicTacToeGame

    private fun generateNumberToClaim() = (0 until TicTacToeGame.FIELD_SIZE).random()

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
        val squareToClaim = generateNumberToClaim()
        game.claimSquare(squareToClaim)
        assertEquals(
            TicTacToeGame.Player.PLAYER_1,
            (game.field[squareToClaim] as Claimed).player,
            "Field should be claimed by player 1"
        )
    }

    @Test
    fun playersCannotClaimAClaimedSquare() {
        val squareToClaim = generateNumberToClaim()
        game.claimSquare(squareToClaim)
        assertThrows<IllegalArgumentException>("Claiming a square twice should throw Exception") {
            game.claimSquare(squareToClaim)
        }
    }

    @Test
    fun playersWillSwitchAfterTurnPlayed() {
        game.claimSquare(generateNumberToClaim())
        assertEquals(
            TicTacToeGame.Player.PLAYER_2,
            game.activePlayer,
            " After a turn played, activeplayer should be player 2"
        )
    }

    @Test
    fun gameEndsAfter9Turns() {
        repeat(9) { game.claimSquare(it) }
        assertTrue("game should be stopped after 9 turns") { game.ended }
    }

}