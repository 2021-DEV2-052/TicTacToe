package be.kata.tictactoe

import be.kata.game.GameHasEndedException
import be.kata.game.TicTacToeGame
import be.kata.tictactoe.ui.GameViewModel
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.*

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

    @Test
    fun anErrorInGameWillLeadToErrorState() {
        val exception = GameHasEndedException("")
        val mockedGame = mock<TicTacToeGame> {
            on { playTurn(any()) } doThrow exception
        }
        val viewModel = GameViewModel(mock {
            on { createGame() } doReturn mockedGame
        })
        val clickedSquare = 0
        viewModel.handleSquareClicked(clickedSquare)
        assertEquals(
            "UI should have error state",
            TicTacToeActivity.ErrorState(exception),
            viewModel.uiState.value
        )
    }

    @Test
    fun aValidClickShouldBringUiStateBackToNormal() {
        val exception = GameHasEndedException("")
        val clickedErrorSquare = 0
        val clickedNormalSquare = 1
        val mockedGame = mock<TicTacToeGame> {
            on { playTurn(clickedErrorSquare) } doThrow exception
            on { playTurn(clickedNormalSquare) } doReturn mock()
        }
        val viewModel = GameViewModel(mock {
            on { createGame() } doReturn mockedGame
        })
        viewModel.handleSquareClicked(clickedErrorSquare)
        viewModel.handleSquareClicked(clickedNormalSquare)
        assertEquals(
            "UI should have error state",
            TicTacToeActivity.Normal,
            viewModel.uiState.value
        )
    }

    @Test
    fun aValidClickShouldShowNewGameState() {
        val mockState: TicTacToeGame.State = mock()
        val clickedSquare = 1
        val mockedGame = mock<TicTacToeGame> {
            on { playTurn(clickedSquare) } doReturn mockState
        }
        val viewModel = GameViewModel(mock {
            on { createGame() } doReturn mockedGame
        })
        viewModel.handleSquareClicked(clickedSquare)
        assertEquals(
            "UI should have error state",
            mockState,
            viewModel.gameState.value
        )
    }
}