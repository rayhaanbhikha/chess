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

    // name = color + pieceType + initialPosition
    def name: String = s"${color.charAt(0)}${pieceType.charAt(0)}$initialPosition"

    def getAvailableMoves(chessBoardSquares: Map[String, ChessBoardSquare]): List[String]
}
