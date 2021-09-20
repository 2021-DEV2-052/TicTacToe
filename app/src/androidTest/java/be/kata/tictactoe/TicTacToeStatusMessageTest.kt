package be.kata.tictactoe

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import be.kata.game.TicTacToeGame
import be.kata.tictactoe.compose.screen.TicTacToeStatusText
import org.junit.Rule
import org.junit.Test

class TicTacToeStatusMessageTest {
    @get:Rule
    val composeRule = createComposeRule()

    @ExperimentalFoundationApi
    @Test
    fun testScreenHasWinMessage() {
        composeRule.setContent {
            TicTacToeStatusText(
                TicTacToeGame.Winner(TicTacToeGame.Player.PLAYER_1)
            )
        }
        composeRule.onNode(hasText("has won", true)).assertIsDisplayed()
    }

    @ExperimentalFoundationApi
    @Test
    fun testScreenHasDrawMessage() {
        composeRule.setContent {
            TicTacToeStatusText(
                TicTacToeGame.Draw
            )
        }
        composeRule.onNode(hasText("draw", true)).assertIsDisplayed()
    }

    @ExperimentalFoundationApi
    @Test
    fun testScreenHasPlayingMessage() {
        composeRule.setContent {
            TicTacToeStatusText(
                TicTacToeGame.Playing(TicTacToeGame.Player.PLAYER_1)
            )
        }
        composeRule.onNode(hasText("Turn", true)).assertIsDisplayed()
    }
}