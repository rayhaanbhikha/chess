package com.rayhaanbhikha.chess.pieces

import com.rayhaanbhikha.chess.board.ChessBoardSquare
import com.rayhaanbhikha.chess.services.Translation

trait ChessPiece {
    val pieceType: String = this.getClass.getSimpleName.toLowerCase
    val value: Int
    val color: String
    val initialPosition: String
    var currentPosition: String = initialPosition


    def possibleMoves: List[Translation]

    def active: Boolean = {
      if(currentPosition.equals(initialPosition))
        false
      else
        true
    }

    def movedUpTwo: Boolean = {
        val initialRow = initialPosition.charAt(1).toInt - 48
        val newRow = currentPosition.charAt(1).toInt - 48

        if(Math.abs(initialRow - newRow) == 2)
            true
        else
            false
    }

    // name = color + pieceType + initialPosition
    def name: String = s"${color.charAt(0).toUpper}${pieceType.charAt(0).toUpper}$initialPosition"

    def getAvailableMoves(chessBoardSquares: Map[String, ChessBoardSquare]): List[String]

    def movePiece(newPosition: String, chessBoardSquares: Map[String, ChessBoardSquare]): Unit
}
