package be.kata.game

class GameHasEndedException(override val message: String) : IllegalStateException()
class SquareAlreadyClaimedException(override val message: String) : IllegalStateException()