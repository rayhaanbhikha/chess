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
    var filteredMoves: List[String] = List()

    var verticalBlock: Boolean = false

    availableMoves.foreach(availableMove => {
      val translation = availableMove.translation
      val position = availableMove.position
      val chessBoardSquare = chessBoardSquares(position)

      // if diagonal -> check is chess board square is not empty.
      if(translation.isDiagonal() && // check diagonal
          !chessBoardSquare.isEmpty && // check not empty
          chessBoardSquare.chessPiece.color != color
      ) {
        filteredMoves = position :: filteredMoves
      }

      // if vertical check chess board square is not empty.
      if(translation.isVertical() && !verticalBlock && chessBoardSquare.isEmpty) {
        filteredMoves = position :: filteredMoves
      } else if (translation.isVertical() && !chessBoardSquare.isEmpty) {
        // chessboard is blocked in vertical direction and therefore cannot go further.
        verticalBlock = true
      }
    })

    filteredMoves
  }
}
