package com.rayhaanbhikha.chess.pieces

import com.rayhaanbhikha.chess.board.ChessBoardSquare

trait ChessPiece {

    val name: String
    val pieceType: String = this.getClass.getSimpleName.toLowerCase
    val utfImage: String
    val value: Int
    var currentPosition: String

    def initialPosition: String = name.substring(2)

    def color: String = name.charAt(0) match {
        case 'W' | 'w' => "white"
        case 'B' | 'b' => "black"
    }

    def active: Boolean = {
      if(currentPosition.equals(initialPosition))
        false
      else
        true
    }

    def getAvailableMoves(chessBoardSquares: Map[String, ChessBoardSquare]): List[String]

    def movePiece(newPosition: String, chessBoardSquares: Map[String, ChessBoardSquare]): Unit = {

        // 1. move selected piece to new position.
        chessBoardSquares(newPosition).chessPiece = this

        // 2. remove selected piece from it's previous position. (if it exists)
        chessBoardSquares(this.currentPosition).removeChessPiece()

        // 3. update pawns current position
        this.currentPosition = newPosition
    }
}
