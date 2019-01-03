package com.rayhaanbhikha.chess.pieces
import com.rayhaanbhikha.chess.Game.Player
import com.rayhaanbhikha.chess.board.{Board, ChessBoardSquare}
import com.rayhaanbhikha.chess.services.{GenericChessPiece, Translation}


case class Bishop(override val name: String,
                  override var currentPosition: String,
                  override val player: Player) extends ChessPiece {
  override val value: Int = 3
  override val utfImage: String = color match {
    case "white" => "\u2657"
    case "black" => "\u265D"
  }


  def possibleMoves: Map[String, List[Translation]] = Map(
    "mNorthEast" -> Board.rows.map(row => Translation(row, row)),
    "mSouthEast" -> Board.rows.map(row => Translation(row, -row)),
    "mSouthWest" -> Board.rows.map(row => Translation(-row, -row)),
    "mNorthWest" -> Board.rows.map(row => Translation(-row, row))
  )

  override def getAvailableMoves(chessBoardSquares: Map[String, ChessBoardSquare]): List[String] = {
      GenericChessPiece.getMoves(chessBoardSquares, possibleMoves, currentPosition, color)
  }
}
