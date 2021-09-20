package be.kata.tictactoe

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import be.kata.game.Nothing
import be.kata.game.TicTacToeGame.Companion.FIELD_SIZE
import be.kata.tictactoe.compose.screen.TicTacToeGrid
import org.junit.Assert
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
                boardState = List(FIELD_SIZE) { Nothing },
                onSquareClick = { }
            )
        }
        composeRule.onRoot().onChild().onChildren().assertCountEquals(9)
    }

    @ExperimentalFoundationApi
    @Test
    fun testGridDelegatesClickBasedOnIndex() {
        var number = 0
        val onClick: (Int) -> Unit = { number = it }
        composeRule.setContent {
            TicTacToeGrid(
                boardState = List(FIELD_SIZE) { Nothing },
                onSquareClick = onClick
            )
        }
        val ordinal = 4
        composeRule.onRoot().onChild().onChildAt(ordinal).performClick()
        Assert.assertEquals(
            "A click should be handled, a squareOrdinal remembered based on index",
            ordinal,
            number
        )
    }
}