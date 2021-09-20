package be.kata.tictactoe

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import be.kata.game.SquareAlreadyClaimedException
import be.kata.tictactoe.compose.screen.TicTacToeInfoText
import org.junit.Rule
import org.junit.Test

class TicTacToeInfoTextTest {

    @get:Rule
    val composeRule = createComposeRule()

    @ExperimentalFoundationApi
    @Test
    fun testScreenHasInfoWhenClickingClaimedSquare() {
        composeRule.setContent {
            TicTacToeInfoText(
                TicTacToeActivity.ErrorState(exception = SquareAlreadyClaimedException(""))
            )
        }
        composeRule.onNode(hasText("already claimed", true)).assertIsDisplayed()
    }
}