package com.rayhaanbhikha.chess.pieces

import com.rayhaanbhikha.chess.board.ChessBoardSquare
import com.rayhaanbhikha.chess.services.{AvailableMove, Translation}

case class Pawn(color: String, initialPosition: String) extends ChessPiece {

  override val value: Int = 1
  var basicMoves: List[Translation] = List(Translation(0,1), Translation(1,1), Translation(-1,1))


  override def possibleMoves: List[Translation] = color match {
    case "white" => moves
    case "black" => Translation.flip(moves)
  }

  def moves: List[Translation] = {
    if(active)
      basicMoves
    else
      Translation(0,2) :: basicMoves
  }

  override def getAvailableMoves(chessBoardSquares: Map[String, ChessBoardSquare]): List[String] = {
    // transform possible moves into actual moves.
    val availableMoves: List[AvailableMove] = Translation.convertTranslations(possibleMoves.sortWith(_.y < _.y), currentPosition)
    // verify each possible move.
    filterAvailableMoves(availableMoves, chessBoardSquares)
  }

  def filterAvailableMoves(availableMoves: List[AvailableMove], chessBoardSquares: Map[String, ChessBoardSquare]): List[String] = {
    val sortedAvailableMoves = AvailableMove.sort(color, availableMoves)


    val diagonalMoves = sortedAvailableMoves
      .filter(_.translation.isDiagonal())
      .flatMap(diagonalCheck(_, chessBoardSquares))

    val verticalMoves = sortedAvailableMoves
      .filter(_.translation.isVertical())
      .takeWhile(move => chessBoardSquares(move.position).isEmpty) // check if empty
      .flatMap(verticalCheck(_, chessBoardSquares))

    diagonalMoves ::: verticalMoves
  }

  def diagonalCheck(availableMove: AvailableMove, chessBoardSquares: Map[String, ChessBoardSquare]): Option[String] = {

    val position = availableMove.position
    val chessBoardSquare = chessBoardSquares(position)
    if(
      !chessBoardSquare.isEmpty &&
      chessBoardSquare.chessPiece.color != color // not same color
    )
      Some(position)
    else
      None
  }

  def verticalCheck(availableMove: AvailableMove,
                    chessBoardSquares: Map[String, ChessBoardSquare]): Option[String] = {

    val position = availableMove.position
    val chessBoardSquare = chessBoardSquares(position)

    if(chessBoardSquare.isEmpty) {
      Some(position)
    } else {
      None
    }
  }
}
