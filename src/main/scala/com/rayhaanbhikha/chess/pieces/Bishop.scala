package com.rayhaanbhikha.chess.pieces
import com.rayhaanbhikha.chess.Game.Player
import com.rayhaanbhikha.chess.board.{Board, ChessBoardSquare}
import com.rayhaanbhikha.chess.services.{GenericChessPiece, Translation}


case class Bishop(override val name: String,
                  override var currentPosition: String,
                  override val player: Player) extends ChessPiece {
  override val value: Int = 3

  def possibleMoves: Map[String, List[Translation]] = {
    var mNorthEast: List[Translation] = List()
    var mNorthWest: List[Translation] = List()
    var mSouthWest: List[Translation] = List()
    var mSouthEast: List[Translation] = List()

      for( row <- Board.rows.reverse) {
        mNorthEast = Translation(row, row) :: mNorthEast
        mSouthEast = Translation(row, -row) :: mSouthEast
        mSouthWest = Translation(-row, -row) :: mSouthWest
        mNorthWest = Translation(-row, row) :: mNorthWest
      }
    Map(
      "mNorthEast" -> mNorthEast,
      "mSouthEast" -> mSouthEast,
      "mSouthWest" -> mSouthWest,
      "mNorthWest" -> mNorthWest
    )
  }

  override def getAvailableMoves(chessBoardSquares: Map[String, ChessBoardSquare]): List[String] = {
      GenericChessPiece.getMoves(chessBoardSquares, possibleMoves, currentPosition, color)
  }
}
