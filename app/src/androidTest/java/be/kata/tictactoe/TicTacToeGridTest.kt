package be.kata.tictactoe

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChild
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onRoot
import be.kata.game.Nothing
import be.kata.game.TicTacToeGame
import be.kata.game.TicTacToeGame.Companion.FIELD_SIZE
import be.kata.tictactoe.compose.screen.TicTacToeGrid
import org.junit.Rule
import org.junit.Test

class TicTacToeGridTest {

    @get:Rule
    val composeRule = createComposeRule()

    @ExperimentalFoundationApi
    @Test
    fun testGridHas9Squares() {
        composeRule.setContent {
            TicTacToeGrid(
                game = TicTacToeGame.State(
                    List(FIELD_SIZE) { Nothing },
                    TicTacToeGame.Playing(TicTacToeGame.Player.PLAYER_1)
                )
            )
        }
        composeRule.onRoot().onChild().onChildren().assertCountEquals(9)
    }
}