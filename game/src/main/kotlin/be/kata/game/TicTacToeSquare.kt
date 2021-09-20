package be.kata.game

sealed class TicTacToeSquare {
    abstract val player: TicTacToeGame.Player?
    abstract fun claim(player: TicTacToeGame.Player) : TicTacToeSquare
}

object Nothing: TicTacToeSquare() {
    override val player: TicTacToeGame.Player? = null
    override fun claim(player: TicTacToeGame.Player): TicTacToeSquare = Claimed(player)
}

data class Claimed(override val player: TicTacToeGame.Player): TicTacToeSquare() {
    override fun claim(player: TicTacToeGame.Player): TicTacToeSquare {
        throw SquareAlreadyClaimedException("You cannot claim a square twice")
    }
}
