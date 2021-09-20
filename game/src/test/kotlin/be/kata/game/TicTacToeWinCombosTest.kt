package be.kata.game

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class TicTacToeWinCombosTest {

    private lateinit var game: TicTacToeGame

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
        assertEquals(
            TicTacToeGame.Winner(TicTacToeGame.Player.PLAYER_1),
            gameState.status,
            "game should be stopped after a win combo is played"
        )
    }

    @Test
    fun gameEndsIfHorizontalMiddleIsNoticed() {
        game.playTurn(3)
        game.playTurn(0)
        game.playTurn(4)
        game.playTurn(1)
        val gameState = game.playTurn(5)
        assertEquals(
            TicTacToeGame.Winner(TicTacToeGame.Player.PLAYER_1),
            gameState.status,
            "game should be stopped after a win combo is played"
        )
    }

    @Test
    fun gameEndsIfHorizontalLowerIsNoticed() {
        game.playTurn(6)
        game.playTurn(3)
        game.playTurn(7)
        game.playTurn(4)
        val gameState = game.playTurn(8)
        assertEquals(
            TicTacToeGame.Winner(TicTacToeGame.Player.PLAYER_1),
            gameState.status,
            "game should be stopped after a win combo is played"
        )
    }

    @Test
    fun gameEndsIfVerticalLeftIsNoticed() {
        game.playTurn(0)
        game.playTurn(1)
        game.playTurn(3)
        game.playTurn(4)
        val gameState = game.playTurn(6)
        assertEquals(
            TicTacToeGame.Winner(TicTacToeGame.Player.PLAYER_1),
            gameState.status,
            "game should be stopped after a win combo is played"
        )
    }

    @Test
    fun gameEndsIfVerticalMiddleIsNoticed() {
        game.playTurn(4)
        game.playTurn(3)
        game.playTurn(1)
        game.playTurn(0)
        val gameState = game.playTurn(7)
        assertEquals(
            TicTacToeGame.Winner(TicTacToeGame.Player.PLAYER_1),
            gameState.status,
            "game should be stopped after a win combo is played"
        )
    }

    @Test
    fun gameEndsIfVerticalRightIsNoticed() {
        game.playTurn(5)
        game.playTurn(3)
        game.playTurn(8)
        game.playTurn(4)
        val gameState = game.playTurn(2)
        assertEquals(
            TicTacToeGame.Winner(TicTacToeGame.Player.PLAYER_1),
            gameState.status,
            "game should be stopped after a win combo is played"
        )
    }

    @Test
    fun gameEndsIfDiagonalUpperLeftToLowerRightIsNoticed() {
        game.playTurn(0)
        game.playTurn(3)
        game.playTurn(4)
        game.playTurn(1)
        val gameState = game.playTurn(8)
        assertEquals(
            TicTacToeGame.Winner(TicTacToeGame.Player.PLAYER_1),
            gameState.status,
            "game should be stopped after a win combo is played"
        )
    }

    @Test
    fun gameEndsIfDiagonalUpperRightToLowerLeftIsNoticed() {
        game.playTurn(4)
        game.playTurn(3)
        game.playTurn(6)
        game.playTurn(0)
        val gameState = game.playTurn(2)
        assertEquals(
            TicTacToeGame.Winner(TicTacToeGame.Player.PLAYER_1),
            gameState.status,
            "game should be stopped after a win combo is played"
        )
    }
}