package be.kata.tictactoe

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import be.kata.tictactoe.compose.theme.TicTacToeAppTheme

class TicTacToeActivity : AppCompatActivity() {

    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TicTacToeAppTheme {

            }
        }
    }

    sealed class UIState
    data class ErrorState(val exception: Exception) : UIState()
    object Normal : UIState()
}


