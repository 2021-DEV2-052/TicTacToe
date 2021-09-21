package be.kata.tictactoe.ui

import androidx.lifecycle.ViewModel
import be.kata.tictactoe.TicTacToeActivity
import be.kata.tictactoe.data.GameCreator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class GameViewModel(private val gameCreator: GameCreator) : ViewModel(){

    val uiState: StateFlow<TicTacToeActivity.UIState>  = MutableStateFlow(TicTacToeActivity.ErrorState(Exception()))
}