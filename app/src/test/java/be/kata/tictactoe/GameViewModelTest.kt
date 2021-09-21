package be.kata.tictactoe

import be.kata.game.TicTacToeGame
import be.kata.tictactoe.ui.GameViewModel
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock

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

    @Test
    fun gameBoardWillBeExposed() {
        val viewModel = GameViewModel(mock())
        assertEquals(
            "Game should start in playing state",
            TicTacToeActivity.Normal,
            viewModel.gameState.value.status == TicTacToeGame.Playing(TicTacToeGame.Player.PLAYER_1)
        )
    }
}