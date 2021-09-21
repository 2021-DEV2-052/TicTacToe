package be.kata.tictactoe.data

import be.kata.game.TicTacToeGame

interface GameCreator {

    fun createGame(): TicTacToeGame
}