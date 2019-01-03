package com.rayhaanbhikha.chess.pieces
import com.rayhaanbhikha.chess.Game.Player
import com.rayhaanbhikha.chess.board.ChessBoardSquare
import com.rayhaanbhikha.chess.services.{AvailableMove, Translation}

case class Knight(override val name: String,
                  override var currentPosition: String,
                  override val player: Player) extends ChessPiece {
  override val value: Int = 3
  override val utfImage: String = color match {
    case "white" => "\u2658"
    case "black" => "\u265E"
  }

  def possibleMoves: List[Translation] = {
    List(
      Translation(-1, 2),
      Translation(1, 2),
      Translation(2, 1),
      Translation(2, -1),
      Translation(1, -2),
      Translation(-1, -2),
      Translation(-2, 1),
      Translation(-2, -1)
    )
  }

  override def getAvailableMoves(chessBoardSquares: Map[String, ChessBoardSquare]): List[String] = {
    val availableMoves: List[AvailableMove] = Translation.convertTranslations(possibleMoves, currentPosition)

    availableMoves.flatMap(filterMove(_, chessBoardSquares))
  }

  def filterMove(availableMove: AvailableMove, chessBoardSquares: Map[String, ChessBoardSquare]): Option[String] = {
    val position = availableMove.position
    val chessBoardSquare = chessBoardSquares(position)

    if(chessBoardSquare.isEmpty)
      Some(position)
    else if(chessBoardSquare.chessPiece.color != color)
      Some(position)
    else
      None
  }
}
