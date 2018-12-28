package com.rayhaanbhikha.chess.pieces
import com.rayhaanbhikha.chess.board.ChessBoardSquare
import com.rayhaanbhikha.chess.services.Translation

class Rook(override val name: String, override var currentPosition: String) extends ChessPiece {
  override val value: Int = 5

  override def possibleMoves: List[Translation] = List()

  override def getAvailableMoves(chessBoardSquares: Map[String, ChessBoardSquare]): List[String] = List()

  override def movePiece(newPosition: String, chessBoardSquares: Map[String, ChessBoardSquare]): Unit = {}
}
