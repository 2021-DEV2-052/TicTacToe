package be.kata.game

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class TicTacToeWinCombosTest {

    private lateinit var game: TicTacToeGame

    private fun generateNumberToClaim() = (0 until TicTacToeGame.FIELD_SIZE).random()

    @BeforeEach
    fun setup() {
        game = TicTacToeGame()
    }

    @Test
    fun gameEndsIfHorizontalUpperIsNoticed() {
        game.playTurn(0)
        game.playTurn(3)
        game.playTurn(1)
        game.playTurn(4)
        val gameState = game.playTurn(2)
        assertTrue("game should be stopped after a win combo is played") { gameState.ended }
    }

    @Test
    fun gameEndsIfHorizontalMiddleIsNoticed() {
        game.playTurn(3)
        game.playTurn(0)
        game.playTurn(4)
        game.playTurn(1)
        val gameState = game.playTurn(5)
        assertTrue("game should be stopped after a win combo is played") { gameState.ended }
    }

    @Test
    fun gameEndsIfHorizontalLowerIsNoticed() {
        game.playTurn(6)
        game.playTurn(3)
        game.playTurn(7)
        game.playTurn(4)
        val gameState = game.playTurn(8)
        assertTrue("game should be stopped after a win combo is played") { gameState.ended }
    }

    @Test
    fun gameEndsIfVerticalLeftIsNoticed() {
        game.playTurn(0)
        game.playTurn(1)
        game.playTurn(3)
        game.playTurn(4)
        val gameState = game.playTurn(6)
        assertTrue("game should be stopped after a win combo is played") { gameState.ended }
    }

    @Test
    fun gameEndsIfVerticalMiddleIsNoticed() {
        game.playTurn(4)
        game.playTurn(3)
        game.playTurn(1)
        game.playTurn(0)
        val gameState = game.playTurn(7)
        assertTrue("game should be stopped after a win combo is played") { gameState.ended }
    }

    @Test
    fun gameEndsIfVerticalRightIsNoticed() {
        game.playTurn(5)
        game.playTurn(3)
        game.playTurn(8)
        game.playTurn(4)
        val gameState = game.playTurn(2)
        assertTrue("game should be stopped after a win combo is played") { gameState.ended }
    }

    @Test
    fun gameEndsIfDiagonalUpperLeftToLowerRightIsNoticed() {
        game.playTurn(0)
        game.playTurn(3)
        game.playTurn(4)
        game.playTurn(1)
        val gameState = game.playTurn(8)
        assertTrue("game should be stopped after a win combo is played") { gameState.ended }
    }

    @Test
    fun gameEndsIfDiagonalUpperRightToLowerLeftIsNoticed() {
        game.playTurn(4)
        game.playTurn(3)
        game.playTurn(6)
        game.playTurn(0)
        val gameState = game.playTurn(2)
        assertTrue("game should be stopped after a win combo is played") { gameState.ended }
    }
}