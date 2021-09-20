package be.kata.tictactoe

import androidx.compose.ui.test.assertContentDescriptionContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChild
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import be.kata.game.Claimed
import be.kata.game.Nothing
import be.kata.game.TicTacToeGame
import be.kata.tictactoe.compose.screen.TicTacToeSquareView
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class TicTacToeSquareTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun testSquareHasOrdinalWhenClicked() {
        var number = 0
        val onClick: (Int) -> Unit = { number = it }
        val ordinal = 20
        composeRule.setContent {
            TicTacToeSquareView(
                squareState = Claimed(TicTacToeGame.Player.PLAYER_2),
                ordinal = ordinal,
                onClick = onClick
            )
        }
        composeRule.onRoot().performClick()
        Assert.assertEquals(
            "A click should be handled, a squareOrdinal remembered",
            ordinal,
            number
        )
    }

    @Test
    fun testSquareShowsCorrectImage1() {
        composeRule.setContent {
            TicTacToeSquareView(
                squareState = Claimed(TicTacToeGame.Player.PLAYER_1),
                ordinal = 0,
                onClick = { }
            )
        }
        composeRule.onRoot().onChild().assertContentDescriptionContains("x")
    }

    @Test
    fun testSquareShowsCorrectImage2() {
        composeRule.setContent {
            TicTacToeSquareView(
                squareState = Claimed(TicTacToeGame.Player.PLAYER_2),
                ordinal = 0,
                onClick = { }
            )
        }
        composeRule.onRoot().onChild().assertContentDescriptionContains("0")
    }

    @Test
    fun testSquareShowsCorrectImageNothing() {
        composeRule.setContent {
            TicTacToeSquareView(squareState = Nothing, ordinal = 0, onClick = { })
        }
        composeRule.onRoot().onChild().assertContentDescriptionContains("nothing")
    }
}