package com.rayhaanbhikha.chess.board

import com.rayhaanbhikha.chess.Game.ChessPieces
import com.rayhaanbhikha.chess.pieces.ChessPiece

class ChessBoard {

    val chessPieces: List[ChessPiece] =  ChessPieces("white") ::: ChessPieces("black")

    private val columns: List[Char] = Board.columns
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

        // place chess pieces on board.
        for(chessPiece <- chessPieces) {
            val position: String = chessPiece.currentPosition
            chessBoardSquares(position).chessPiece_=(chessPiece)
        }
    }

    def select(chessPieceName: String): List[String] = {
        val selectedChessPiece: ChessPiece = getChessPiece(chessPieceName)
        selectedChessPiece.getAvailableMoves(chessBoardSquares)
    }


    def move(chessPieceName: String, newPosition: String): Unit = {
        val selectedChessPiece: ChessPiece = getChessPiece(chessPieceName)
        selectedChessPiece.movePiece(newPosition, chessBoardSquares)
    }

    @throws(classOf[NoSuchElementException])
    def getChessPiece(chessPieceName: String): ChessPiece =
        chessPieces.find(_.name == chessPieceName).get

}