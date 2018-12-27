package com.rayhaanbhikha.chess.pieces

import com.rayhaanbhikha.chess.board.ChessBoardSquare
import com.rayhaanbhikha.chess.services.Translation

trait ChessPiece {

    val name: String
    val pieceType: String = this.getClass.getSimpleName.toLowerCase
    val value: Int
    var currentPosition: String

    def initialPosition: String = name.substring(2)

    def color: String = name.charAt(0) match {
        case 'W' | 'w' => "white"
        case 'B' | 'b' => "black"
    }

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

    def getAvailableMoves(chessBoardSquares: Map[String, ChessBoardSquare]): List[String]

    def movePiece(newPosition: String, chessBoardSquares: Map[String, ChessBoardSquare]): Unit
}
