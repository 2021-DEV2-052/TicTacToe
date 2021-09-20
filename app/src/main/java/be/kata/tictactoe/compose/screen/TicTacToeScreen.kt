package be.kata.tictactoe.compose.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import be.kata.game.Claimed
import be.kata.game.Nothing
import be.kata.game.TicTacToeGame
import be.kata.game.TicTacToeSquare
import be.kata.tictactoe.R

@Composable
fun TicTacToeSquareView(
    squareState: TicTacToeSquare,
    ordinal: Int,
    size: Dp = (LocalConfiguration.current.screenWidthDp / 3).dp,
    onClick: (Int) -> Unit,
) {
    Surface(
        modifier = Modifier
            .size(size)
            .padding(4.dp)
            .background(color = Color.Gray, shape = RoundedCornerShape(4.dp))
            .clickable(onClick = { onClick(ordinal) })
    ) {
        when (squareState) {
            Nothing -> {
                Image(
                    painter = painterResource(id = R.drawable.empty),
                    contentDescription = "nothing",
                    modifier = Modifier.fillMaxSize().background(Color.Transparent)
                )
            }
            is Claimed -> {
                when (squareState.player) {
                    TicTacToeGame.Player.PLAYER_1 -> {
                        Image(
                            painter = painterResource(id = R.drawable.cross),
                            contentDescription = "x",
                            modifier = Modifier.fillMaxSize().background(Color.Transparent)
                        )
                    }
                    TicTacToeGame.Player.PLAYER_2 -> {
                        Image(
                            painter = painterResource(id = R.drawable.circle),
                            contentDescription = "0",
                            modifier = Modifier.fillMaxSize().background(Color.Transparent)
                        )
                    }
                }
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun TicTacToeGrid(game: TicTacToeGame.State, onSquareClick: (Int) -> Unit) {
    LazyVerticalGrid(cells = GridCells.Fixed(3), modifier = Modifier.fillMaxWidth()) {
        itemsIndexed(game.field) { index, squareState ->
            TicTacToeSquareView(squareState, index) { onSquareClick(it) }
        }
    }
}