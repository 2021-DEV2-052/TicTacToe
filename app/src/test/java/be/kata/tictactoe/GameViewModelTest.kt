package be.kata.tictactoe

import be.kata.game.TicTacToeGame
import be.kata.tictactoe.ui.GameViewModel
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class GameViewModelTest {

    @Test
    fun gameUIWillStartNormal() {
        val viewModel = GameViewModel(mock {
            on { createGame() } doReturn TicTacToeGame()
        })
        assertEquals(
            "UI should start in normal state",
            TicTacToeActivity.Normal,
            viewModel.uiState.value
        )
    }

    @Test
    fun gameBoardWillBeExposed() {
        val viewModel = GameViewModel(mock {
            on { createGame() } doReturn TicTacToeGame()
        })
        assertEquals(
            "Game should start in playing state",
            TicTacToeGame.Playing(TicTacToeGame.Player.PLAYER_1),
            viewModel.gameState.value.status
        )
    }
}