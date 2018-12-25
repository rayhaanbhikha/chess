package com.rayhaanbhikha.chess.board

import com.rayhaanbhikha.chess.pieces.ChessPiece
import scala.collection.mutable.ArrayBuffer

class ChessBoard(chessPieces: ArrayBuffer[ChessPiece]) {
    var columns: Array[Char] = Array('a','b','c','d','e','f','g','h')
    var chessBoardSquares: Map[String, ChessBoardSquare] = Map()
    initialiseChessBoardSquares()

    // maybe instead of rendering chessboard all the time do on the two chess board squares that have changed.
    def initialiseChessBoardSquares(): Unit = {
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
    }

    // initialiseChessPiece on chessboard
    def placeChessPiece(chessPiece: ChessPiece, chessBoardSquares: Map[String, ChessBoardSquare]): Unit = {
        val position: String = chessPiece.currentPosition
        val chessBoardSquare: ChessBoardSquare = chessBoardSquares(position)
        chessBoardSquare.chessPiece_=(chessPiece)
    }

    def select(chessPieceName: String): Unit = {
        try {
            val selectedChessPiece: ChessPiece = getChessPiece(chessPieceName)
            val availableMoves: List[String] = selectedChessPiece.getAvailableMoves(chessBoardSquares)
            println(s"\nAvailable moves: $chessPieceName")
            availableMoves.foreach(move => print(s"$move\t"))
            println()
        } catch {
            case _: NoSuchElementException =>
                println(s"$chessPieceName does not exist. Please try again")
        }
    }


    def move(chessPieceName: String, newPosition: String): Unit = {
        val selectedChessPiece: ChessPiece = getChessPiece(chessPieceName)

        // move Chess Piece to new position.
        chessBoardSquares(newPosition).chessPiece_=(selectedChessPiece)

        // remove chessPiece from prev position.
        chessBoardSquares(selectedChessPiece.currentPosition).removeChessPiece()

        // update 'current position' on selectedPiece
        selectedChessPiece.currentPosition = newPosition

    }

    def getChessPiece(chessPieceName: String): ChessPiece =
        chessPieces.find(chessPiece => chessPiece.name == chessPieceName).get



    def printBoard(): Unit = PrintBoard(chessBoardSquares)
}