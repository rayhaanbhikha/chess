package com.rayhaanbhikha.chess.pieces

import com.rayhaanbhikha.chess.board.ChessBoardSquare
import com.rayhaanbhikha.chess.services.{AvailableMove, Translation}

case class Pawn(name: String, override var currentPosition: String) extends ChessPiece {

  override val value: Int = 1
  var basicMoves: () => List[Translation] = () => List(Translation(0,1), Translation(1,1), Translation(-1,1))

  def getMoves: List[Translation] = {
    if(active)
      basicMoves()
    else
      Translation(0,2) :: basicMoves()
  }

  override def possibleMoves: List[Translation] = color match {
      case "white" => getMoves
      case "black" => getMoves.map(Translation.flipSign)
  }

  override def getAvailableMoves(chessBoardSquares: Map[String, ChessBoardSquare]): List[String] = {
    // transform possible moves into actual moves.
    val availableMoves: List[AvailableMove] = Translation.convertTranslations(possibleMoves.sortWith(_.y < _.y), currentPosition)
    // verify each possible move.
    filterAvailableMoves(availableMoves, chessBoardSquares)
  }

  def filterAvailableMoves(availableMoves: List[AvailableMove], chessBoardSquares: Map[String, ChessBoardSquare]): List[String] = {
    val sortedAvailableMoves = AvailableMove.sort(color, availableMoves) // sort translation vectors depending on color of pawn.

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

  def verticalCheck(availableMove: AvailableMove, chessBoardSquares: Map[String, ChessBoardSquare]): Option[String] = {

    val position = availableMove.position
    val chessBoardSquare = chessBoardSquares(position)

    if(chessBoardSquare.isEmpty) {
      Some(position)
    } else {
      None
    }
  }

  override def movePiece(newPosition: String, chessBoardSquares: Map[String, ChessBoardSquare]): Unit = {

    // 1. move selected piece to new position.
    chessBoardSquares(newPosition).chessPiece = this

    // 2. remove selected piece from it's previous position.
    chessBoardSquares(this.currentPosition).removeChessPiece()

    // 3. update pawns current position
    this.currentPosition = newPosition
  }
}
