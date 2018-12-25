package com.rayhaanbhikha.chess.board

import com.rayhaanbhikha.chess.pieces.ChessPiece
import scala.collection.mutable.ArrayBuffer

class ChessBoard(chessPieces: ArrayBuffer[ChessPiece]) {
    var columns: Array[Char] = Array('a','b','c','d','e','f','g','h')

    // maybe instead of rendering chessboard all the time do on the two chessboardsquares that have changed.
    def chessBoardSquares: Map[String, ChessBoardSquare] = {
        var chessBoardSquares: Map[String, ChessBoardSquare] = Map()

        // map all chess board squares
        for(row <- columns.indices.reverse) {
            for(col <- columns) {
                val position: String = s"$col${row+1}"
                val chessBoardSquare: ChessBoardSquare = new ChessBoardSquare(position)
                chessBoardSquares += (position -> chessBoardSquare)
            }
        }

        // map all chessboard pieces.
        for(chessPiece <- chessPieces) {
            placeChessPiece(chessPiece, chessBoardSquares)
        }
        chessBoardSquares
    }

    // initialiseChessPiece on chessboard
    def placeChessPiece(chessPiece: ChessPiece, chessBoardSquares: Map[String, ChessBoardSquare]): Unit = {
        val position: String = chessPiece.currentPosition
        val chessBoardSquare: ChessBoardSquare = chessBoardSquares(position)
        chessBoardSquare.chessPiece_=(chessPiece)
    }

    def select(chessPieceName: String): Unit = {
        try {
        val selectedChessPiece: ChessPiece = chessPieces.find(chessPiece => chessPiece.name == chessPieceName).get
        val availableMoves: List[String] = selectedChessPiece.getAvailableMoves(chessBoardSquares)
        println("Available moves: ")
        availableMoves.foreach(move => print(s"$move\t"))

        } catch {
            case _: NoSuchElementException =>
                println(s"$chessPieceName does not exist. Please try again")
        }
    }


    def printBoard(): Unit = PrintBoard(chessBoardSquares)
}