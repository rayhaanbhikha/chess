package com.rayhaanbhikha.chess.services

import com.rayhaanbhikha.chess.board.ChessBoardSquare

import scala.util.control.Breaks.{break, breakable}

object GenericChessPiece {


  def getMoves(
                chessBoardSquares: Map[String, ChessBoardSquare],
                possibleMoves: Map[String, List[Translation]],
                currentPosition: String,
                color: String): List[String] = {
    var movesToReturn: List[String] = List()

    for ((_, moves) <- possibleMoves) {
      // translations -> availableMoves
      val availableMoves: List[AvailableMove] = Translation.convertTranslations(moves, currentPosition)

      // filter List of available moves.
      val filteredAvailableMoves: List[String] = filterMoves(availableMoves, chessBoardSquares, color)

      movesToReturn = filteredAvailableMoves ::: movesToReturn
    }

    movesToReturn
  }


  def filterMoves(availableMoves: List[AvailableMove],
                  chessBoardSquares: Map[String, ChessBoardSquare],
                  color: String): List[String] = {
    var filteredMoves: List[String] = List()

    breakable {
      for(move <- availableMoves) {
        val position = move.position
        val chessBoardSquare = chessBoardSquares(position)

        if (chessBoardSquare.isEmpty) {
          filteredMoves = position :: filteredMoves
        }
        // if collide with other color then add position then break.
        else if (!chessBoardSquare.isEmpty && chessBoardSquare.chessPiece.color != color){
          filteredMoves = position :: filteredMoves
          break
        }
        // if collide with same color just break.
        else if(!chessBoardSquare.isEmpty && chessBoardSquare.chessPiece.color == color) {
          break
        }
      }
    }

    filteredMoves
  }
}
