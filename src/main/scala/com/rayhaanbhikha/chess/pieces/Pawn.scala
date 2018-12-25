package com.rayhaanbhikha.chess.pieces

import com.rayhaanbhikha.chess.board.ChessBoardSquare
import com.rayhaanbhikha.chess.services.{AvailableMove, Translation}

import scala.util.control.Breaks

case class Pawn(color: String, initialPosition: String) extends ChessPiece {

  override val value: Int = 1

  def possibleMoves: List[Translation] = {
    if(active) {
      List[Translation](
        new Translation(0,1),
        new Translation(1,1),
        new Translation(-1,1)
      )
    } else {
      List[Translation](
        new Translation(0,1),
        new Translation(0,2),
        new Translation(1,1),
        new Translation(-1,1)
      )
    }
  }

  override def getAvailableMoves(chessBoardSquares: Map[String, ChessBoardSquare]): List[String] = {
    // transform possible moves into actual moves.

    val availableMoves: List[AvailableMove] = Translation.convertTranslations(possibleMoves, currentPosition)

    // verify each possible move.
    filterAvailableMoves(availableMoves, chessBoardSquares)
  }

  def filterAvailableMoves(availableMoves: List[AvailableMove], chessBoardSquares: Map[String, ChessBoardSquare]): List[String] = {
    var filteredMoves: List[String] = List()

    var verticalBlock: Boolean = false

    availableMoves.foreach(availableMove => {
      val translation = availableMove.translation
      val position = availableMove.position

      // if diagonal -> check is chess board square is not empty.
      if(translation.isDiagonal() && !chessBoardSquares(position).isEmpty) {
        filteredMoves = position :: filteredMoves
      }

      // if vertical check chess board square is not empty.
      if(translation.isVertical() && !verticalBlock && chessBoardSquares(position).isEmpty) {
        filteredMoves = position :: filteredMoves
      } else if (translation.isVertical() && !chessBoardSquares(position).isEmpty) {
        // chessboard is blocked in vertical direction and therefore cannot go further.
        verticalBlock = true
      }
    })

    filteredMoves
  }





}
