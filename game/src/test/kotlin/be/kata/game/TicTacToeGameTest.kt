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
            game.state.activePlayer,
            "A game always starts with player 1"
        )
    }

    @Test
    fun gameExposesAFieldWith9Squares() {
        assertEquals(9, game.state.field.size, "Field should contain 9 squares")
    }

    @Test
    fun playersCanClaimSquare() {
        val squareToClaim = generateNumberToClaim()
        val gameState = game.playTurn(squareToClaim)
        assertEquals(
            TicTacToeGame.Player.PLAYER_1,
            (gameState.field[squareToClaim] as Claimed).player,
            "Field should be claimed by player 1"
        )
    }

    @Test
    fun playersCannotClaimAClaimedSquare() {
        val squareToClaim = generateNumberToClaim()
        game.playTurn(squareToClaim)
        assertThrows<IllegalArgumentException>("Claiming a square twice should throw Exception") {
            game.playTurn(squareToClaim)
        }
    }

    @Test
    fun playersWillSwitchAfterTurnPlayed() {
        val gameState = game.playTurn(generateNumberToClaim())
        assertEquals(
            TicTacToeGame.Player.PLAYER_2,
            gameState.activePlayer,
            " After a turn played, activeplayer should be player 2"
        )
    }

    @Test
    fun gameEndsAfter9Turns() {
        game.playTurn(0)
        game.playTurn(3)
        game.playTurn(1)
        game.playTurn(4)
        game.playTurn(5)
        game.playTurn(2)
        game.playTurn(6)
        game.playTurn(8)
        game.playTurn(7)
        assertTrue("game should be stopped after 9 turns") { game.state.ended }
    }

    @Test
    fun gameEndsIfAWinComboIsNoticed() {
        game.playTurn(0)
        game.playTurn(3)
        game.playTurn(1)
        game.playTurn(4)
        val gameState = game.playTurn(2)
        assertTrue("game should be stopped after a win combo is played") { gameState.ended }
    }

    @Test
    fun playerWinsIfAWinComboIsNoticed() {
        game.playTurn(0)
        game.playTurn(3)
        game.playTurn(1)
        game.playTurn(4)
        val gameState = game.playTurn(2)
        assertEquals(
            TicTacToeGame.Player.PLAYER_1,
            gameState.winner,
            "game should be stopped after a win combo is played"
        )
    }

    @Test
    fun playerCannotPlayOnAnEndedGame() {
        game.playTurn(0)
        game.playTurn(3)
        game.playTurn(1)
        game.playTurn(4)
        game.playTurn(2)
        assertThrows<IllegalStateException>("playing after end is not possible") {
            game.playTurn(5)
        }
    }
}