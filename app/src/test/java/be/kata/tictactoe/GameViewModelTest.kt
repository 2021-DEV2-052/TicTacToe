package be.kata.tictactoe

import be.kata.tictactoe.ui.GameViewModel
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class GameViewModelTest {

    @Test
    fun gameUIWillStartNormal() {
        val viewModel = GameViewModel(mock())
        assertEquals(
            "UI should start in normal state",
            TicTacToeActivity.Normal,
            viewModel.uiState.value
        )
    }
}