package be.kata.tictactoe

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import be.kata.tictactoe.compose.screen.TicTacToeScreen
import be.kata.tictactoe.compose.theme.TicTacToeAppTheme
import be.kata.tictactoe.data.GameCreator
import be.kata.tictactoe.ui.GameViewModel
import org.koin.android.ext.android.inject

class TicTacToeActivity : AppCompatActivity() {

    private val gameCreator: GameCreator by inject()

    private val viewModel: GameViewModel by viewModels { GameViewModel.Factory(gameCreator) }

    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TicTacToeAppTheme {
                TicTacToeScreen(viewModel)
            }
        }
    }

    sealed class UIState
    data class ErrorState(val exception: Exception) : UIState()
    object Normal : UIState()
}


