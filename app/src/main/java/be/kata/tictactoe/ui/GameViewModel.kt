package be.kata.tictactoe.ui

import androidx.lifecycle.ViewModel
import be.kata.game.TicTacToeGame
import be.kata.tictactoe.TicTacToeActivity
import be.kata.tictactoe.data.GameCreator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class GameViewModel(private val gameCreator: GameCreator) : ViewModel() {

    private val game = gameCreator.createGame()

    private val _gameState = MutableStateFlow(game.state)
    val gameState: StateFlow<TicTacToeGame.State> = _gameState

    private val _uiState = MutableStateFlow(TicTacToeActivity.Normal)
    val uiState: StateFlow<TicTacToeActivity.UIState> = _uiState

    fun handleSquareClicked(clickedSquare: Int) {

    }
}