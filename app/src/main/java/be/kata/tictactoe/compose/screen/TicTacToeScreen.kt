package be.kata.tictactoe.compose.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import be.kata.game.TicTacToeSquare

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
    }
}