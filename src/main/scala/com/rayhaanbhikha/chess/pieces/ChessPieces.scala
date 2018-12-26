package com.rayhaanbhikha.chess.pieces

import com.rayhaanbhikha.chess.board.Board

object ChessPieces {
  private val columns = Board.columns
  def apply(): List[ChessPiece] = {
    whitePieces ::: blackPieces
  }

  def whitePieces: List[ChessPiece] = {
    pawns("white", 2)
  }

  def blackPieces: List[ChessPiece] = {
    pawns("black", 7)
  }

  def pawns(color: String, row: Int): List[Pawn] = {
    var pawns: List[Pawn] = List()
    for(col <- columns) {
      pawns = Pawn(color, s"$col$row") :: pawns
    }
    pawns
  }
}
