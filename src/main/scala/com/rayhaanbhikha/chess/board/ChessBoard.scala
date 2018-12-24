package com.rayhaanbhikha.chess.board

import com.rayhaanbhikha.chess.pieces.ChessPiece

import scala.collection.mutable.ArrayBuffer

class ChessBoard(chessPieces: ArrayBuffer[ChessPiece]) {
    var columns: Array[Char] = Array('a','b','c','d','e','f','g','h')
    var chessBoardSquares: Map[String, ChessBoardSquare] = Map()

    def initialiseChessBoard(): Unit = {
        for(row <- columns.indices.reverse) {
            for(col <- columns) {
                val position: String = s"$col${row+1}"
                val chessBoardSquare: ChessBoardSquare = new ChessBoardSquare(position)
                chessBoardSquares += (position -> chessBoardSquare)
            }
        }
        for(chessPiece <- chessPieces) {
            initialiseChessPiece(chessPiece)
        }
    }

    // initialiseChessPiece on chessboard
    def initialiseChessPiece(chessPiece: ChessPiece): Unit = {
        val position: String = chessPiece.currentPosition
        val chessBoardSquare: ChessBoardSquare = chessBoardSquares(position)
        chessBoardSquare.chessPiece_=(chessPiece)
    }



    def printBoard(): Unit = PrintBoard.printBoard(chessBoardSquares)
}