package com.rayhaanbhikha.chess.pieces
import com.rayhaanbhikha.chess.Game.Player
import com.rayhaanbhikha.chess.board.{Board, ChessBoardSquare}
import com.rayhaanbhikha.chess.services.{GenericChessPiece, Translation}

case class Rook(override val name: String,
                override var currentPosition: String,
                override val player: Player) extends ChessPiece {
  override val value: Int = 5
  override val utfImage: String = color match {
    case "white" => "\u2656"
    case "black" => "\u265C"
  }

  def possibleMoves: Map[String, List[Translation]] = Map(
    "mNorth" -> Board.rows.map(row => Translation(0, row)),
    "mEast" -> Board.rows.map(row => Translation(row, 0)),
    "mSouth" -> Board.rows.map(row => Translation(0, -row)),
    "mWest" -> Board.rows.map(row => Translation(-row, 0))
  )

  override def getAvailableMoves(chessBoardSquares: Map[String, ChessBoardSquare]): List[String] = {
    GenericChessPiece.getMoves(chessBoardSquares, possibleMoves, currentPosition, color)
  }
}
