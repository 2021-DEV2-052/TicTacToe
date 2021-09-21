package be.kata.tictactoe.data

import be.kata.game.TicTacToeGame

class DefaultGameCreator : GameCreator {

    override fun createGame(): TicTacToeGame {
        return TicTacToeGame()
    }
}