package be.kata.tictactoe.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import be.kata.game.TicTacToeGame
import be.kata.tictactoe.TicTacToeActivity
import be.kata.tictactoe.data.GameCreator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class GameViewModel(gameCreator: GameCreator) : ViewModel() {

    private val game = gameCreator.createGame()

    private val _gameState = MutableStateFlow(game.state)
    val gameState: StateFlow<TicTacToeGame.State> = _gameState

    private val _uiState = MutableStateFlow<TicTacToeActivity.UIState>(TicTacToeActivity.Normal)
    val uiState: StateFlow<TicTacToeActivity.UIState> = _uiState

    fun handleSquareClicked(clickedSquare: Int) {
        try {
            _gameState.update { game.playTurn(clickedSquare) }
            _uiState.update { TicTacToeActivity.Normal }
        } catch (exception: Exception) {
            _uiState.update { TicTacToeActivity.ErrorState(exception) }
        }
    }

    class Factory(private val gameCreator: GameCreator) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(GameViewModel::class.java)) {
                return GameViewModel(gameCreator) as T
            } else {
                throw IllegalArgumentException("no model class known")
            }
        }
    }
}

