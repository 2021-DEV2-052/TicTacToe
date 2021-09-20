package be.kata.tictactoe

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import be.kata.game.Nothing
import be.kata.game.TicTacToeGame
import be.kata.tictactoe.compose.screen.TicTacToeScreen
import org.junit.Rule
import org.junit.Test

class TicTacToeScreenTest {
    @get:Rule
    val composeRule = createComposeRule()

    @ExperimentalFoundationApi
    @Test
    fun testScreenHasWinMessage() {
        composeRule.setContent {
            TicTacToeScreen(
                gameState = TicTacToeGame.State(
                    List(TicTacToeGame.FIELD_SIZE) { Nothing },
                    TicTacToeGame.Winner(TicTacToeGame.Player.PLAYER_1)
                ),
                onSquareClick = { },
            )
        }
        composeRule.onNode(hasText("has won", true)).assertIsDisplayed()
    }

    @ExperimentalFoundationApi
    @Test
    fun testScreenHasDrawMessage() {
        composeRule.setContent {
            TicTacToeScreen(
                gameState = TicTacToeGame.State(
                    List(TicTacToeGame.FIELD_SIZE) { Nothing },
                    TicTacToeGame.Draw
                ),
                onSquareClick = { },
            )
        }
        composeRule.onNode(hasText("draw", true)).assertIsDisplayed()
    }

    @ExperimentalFoundationApi
    @Test
    fun testScreenHasPlayingMessage() {
        composeRule.setContent {
            TicTacToeScreen(
                gameState = TicTacToeGame.State(
                    List(TicTacToeGame.FIELD_SIZE) { Nothing },
                    TicTacToeGame.Playing(TicTacToeGame.Player.PLAYER_1)
                ),
                onSquareClick = { },
            )
        }
        composeRule.onNode(hasText("turn", true)).assertIsDisplayed()
    }
}