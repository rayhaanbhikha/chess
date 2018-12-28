package com.rayhaanbhikha.chess.pieces
import com.rayhaanbhikha.chess.board.{Board, ChessBoardSquare}
import com.rayhaanbhikha.chess.services.{GenericChessPiece, Translation}

case class Rook(override val name: String, override var currentPosition: String) extends ChessPiece {
  override val value: Int = 5

  def possibleMoves: Map[String, List[Translation]] = {
    var mNorth: List[Translation] = List()
    var mEast: List[Translation] = List()
    var mSouth: List[Translation] = List()
    var mWest: List[Translation] = List()

      for( row <- Board.rows.reverse) {
        mNorth = Translation(0, row) :: mNorth
        mEast = Translation(row, 0) :: mEast
        mSouth = Translation(0, -row) :: mSouth
        mWest = Translation(-row, 0) :: mWest
      }
    Map(
      "mNorth" -> mNorth,
      "mEast" -> mEast,
      "mSouth" -> mSouth,
      "mWest" -> mWest
    )
  }

  override def getAvailableMoves(chessBoardSquares: Map[String, ChessBoardSquare]): List[String] = {
    GenericChessPiece.getMoves(chessBoardSquares, possibleMoves, currentPosition, color)
  }
}
