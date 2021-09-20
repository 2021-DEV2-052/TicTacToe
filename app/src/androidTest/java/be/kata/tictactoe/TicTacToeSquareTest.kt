package be.kata.tictactoe

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
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
}