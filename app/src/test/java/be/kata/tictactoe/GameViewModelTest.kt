package be.kata.tictactoe

import be.kata.game.TicTacToeGame
import be.kata.tictactoe.ui.GameViewModel
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

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

    @Test
    fun clicksWillGoToTheGame() {
        val mockedGame = mock<TicTacToeGame> {}
        val viewModel = GameViewModel(mock {
            on { createGame() } doReturn mockedGame
        })
        val clickedSquare = 0
        viewModel.handleSquareClicked(clickedSquare)
        verify(mockedGame).playTurn(clickedSquare)
    }
}