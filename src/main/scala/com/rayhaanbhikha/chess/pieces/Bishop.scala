package com.rayhaanbhikha.chess.pieces
import com.rayhaanbhikha.chess.board.{Board, ChessBoardSquare}
import com.rayhaanbhikha.chess.services.Translation

case class Bishop(override val name: String, override var currentPosition: String) extends ChessPiece {
  override val value: Int = 3

  override def possibleMoves: List[Translation] = {
      var moves: List[Translation] = List()
      for(row <- Board.rows) {
        moves = Translation(row, row) ::
                Translation(row, -row) ::
                Translation(-row, -row) ::
                Translation(-row, row) ::
                moves
      }
    moves
  }

  override def getAvailableMoves(chessBoardSquares: Map[String, ChessBoardSquare]): List[String] = {
    possibleMoves.foreach(p => println(p.toString))
    List("b")
  }

  override def movePiece(newPosition: String, chessBoardSquares: Map[String, ChessBoardSquare]): Unit = {}
}
