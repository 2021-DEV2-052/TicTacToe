package be.kata.tictactoe.compose.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import be.kata.game.Claimed
import be.kata.game.Nothing
import be.kata.game.TicTacToeGame
import be.kata.game.TicTacToeSquare
import be.kata.tictactoe.R


@ExperimentalFoundationApi
@Preview(showBackground = true)
@Composable
fun TicTacToeScreenPreview() {
    TicTacToeScreen(
        gameState = TicTacToeGame.State(
            List(TicTacToeGame.FIELD_SIZE) { Nothing },
            TicTacToeGame.Playing(TicTacToeGame.Player.PLAYER_1)
        ),
        onSquareClick = { println("clicked $it") },
    )
}

@ExperimentalFoundationApi
@Composable
fun TicTacToeScreen(gameState: TicTacToeGame.State, onSquareClick: (Int) -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.h2,
            color = MaterialTheme.colors.onSurface
        )
        TicTacToeGrid(boardState = gameState.field, onSquareClick = onSquareClick)
    }
}

@ExperimentalFoundationApi
@Composable
fun TicTacToeGrid(boardState: List<TicTacToeSquare>, onSquareClick: (Int) -> Unit) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(3),
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent),
    ) {
        itemsIndexed(boardState) { index, squareState ->
            TicTacToeSquareView(squareState, index, onSquareClick)
        }
    }
}

@Composable
fun TicTacToeSquareView(
    squareState: TicTacToeSquare,
    ordinal: Int,
    onClick: (Int) -> Unit,
    size: Dp = (LocalConfiguration.current.screenWidthDp / 3).dp,
) {
    Surface(
        modifier = Modifier
            .size(size)
            .padding(4.dp)
            .clickable(onClick = { onClick(ordinal) })
    ) {
        when (squareState) {
            Nothing -> {
                Image(
                    painter = painterResource(id = R.drawable.empty),
                    contentDescription = "nothing",
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color.Gray, shape = RoundedCornerShape(4.dp))
                )
            }
            is Claimed -> {
                when (squareState.player) {
                    TicTacToeGame.Player.PLAYER_1 -> {
                        Image(
                            painter = painterResource(id = R.drawable.cross),
                            contentDescription = "x",
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color = Color.Gray, shape = RoundedCornerShape(4.dp))
                        )
                    }
                    TicTacToeGame.Player.PLAYER_2 -> {
                        Image(
                            painter = painterResource(id = R.drawable.circle),
                            contentDescription = "0",
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color = Color.Gray, shape = RoundedCornerShape(4.dp))
                        )
                    }
                }
            }
        }
    }
}