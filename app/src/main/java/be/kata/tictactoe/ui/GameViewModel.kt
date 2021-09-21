package be.kata.tictactoe.ui

import androidx.lifecycle.ViewModel
import be.kata.game.Nothing
import be.kata.game.TicTacToeGame
import be.kata.tictactoe.TicTacToeActivity
import be.kata.tictactoe.data.GameCreator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class GameViewModel(private val gameCreator: GameCreator) : ViewModel() {

    val gameState: StateFlow<TicTacToeGame.State> =
        MutableStateFlow(TicTacToeGame.State(List(9) { Nothing }, TicTacToeGame.Draw))

    private val _uiState: MutableStateFlow<TicTacToeActivity.UIState> =
        MutableStateFlow(TicTacToeActivity.Normal)
    val uiState: StateFlow<TicTacToeActivity.UIState> = _uiState
}