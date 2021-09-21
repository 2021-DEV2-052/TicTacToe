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
import be.kata.game.*
import be.kata.game.Nothing
import be.kata.tictactoe.R
import be.kata.tictactoe.TicTacToeActivity

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
        uiState = TicTacToeActivity.Normal
    )
}

@ExperimentalFoundationApi
@Composable
fun TicTacToeScreen(
    gameState: TicTacToeGame.State,
    onSquareClick: (Int) -> Unit,
    uiState: TicTacToeActivity.UIState
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.h2,
            color = MaterialTheme.colors.onSurface
        )
        TicTacToeGrid(boardState = gameState.field, onSquareClick = onSquareClick)
        TicTacToeStatusText(status = gameState.status)
        TicTacToeInfoText(status = uiState)
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
                                .background(
                                    color = Color.Gray,
                                    shape = RoundedCornerShape(4.dp)
                                )
                        )
                    }
                    TicTacToeGame.Player.PLAYER_2 -> {
                        Image(
                            painter = painterResource(id = R.drawable.circle),
                            contentDescription = "0",
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    color = Color.Gray,
                                    shape = RoundedCornerShape(4.dp)
                                )
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TicTacToeStatusText(status: TicTacToeGame.Status) {
    val text = when (status) {
        is TicTacToeGame.Winner -> {
            stringResource(
                id = R.string.win_message,
                if (status.winningPlayer == TicTacToeGame.Player.PLAYER_1)
                    stringResource(R.string.player1) else stringResource(R.string.player_2)
            )
        }
        is TicTacToeGame.Draw -> {
            stringResource(id = R.string.draw_message)
        }
        is TicTacToeGame.Playing -> {
            stringResource(
                id = R.string.playing_message,
                if (status.activePlayer == TicTacToeGame.Player.PLAYER_1)
                    stringResource(R.string.player1) else stringResource(R.string.player_2)
            )
        }
        else -> throw IllegalArgumentException("this should not happen")
    }
    Text(text = text, color = MaterialTheme.colors.onSurface)
}

@Composable
fun TicTacToeInfoText(status: TicTacToeActivity.UIState) {
    val text = when (status) {
        is TicTacToeActivity.ErrorState ->
            when (status.exception) {
                is GameHasEndedException -> stringResource(id = R.string.game_ended)
                is SquareAlreadyClaimedException -> stringResource(id = R.string.already_claimed)
                else -> throw IllegalArgumentException("this should not happen")
            }

        TicTacToeActivity.Normal -> ""
        else -> throw IllegalArgumentException("this should not happen")
    }
    Text(text = text, color = MaterialTheme.colors.onSurface)
}
