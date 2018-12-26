package com.rayhaanbhikha.chess.pieces

object ChessPieces {
  def apply(): List[ChessPiece] = {
    whitePieces ::: blackPieces
  }

  def whitePieces: List[ChessPiece] = {
    val whitePawn: Pawn = Pawn("white", "a2")

    List(
      whitePawn,
      whitePawn.copy(initialPosition = "b2"),
      whitePawn.copy(initialPosition = "c2"),
      whitePawn.copy(initialPosition = "b6"),
    )
  }

  def blackPieces: List[ChessPiece] = {
    val blackPawn: Pawn = Pawn("black", "a7")

    List(
      blackPawn,
      blackPawn.copy(initialPosition = "b7"),
      blackPawn.copy(initialPosition = "b4"),
    )
  }
}
