package com.rayhaanbhikha.chess.pieces

import com.rayhaanbhikha.chess.board.Board
import com.typesafe.config.{Config, ConfigFactory}

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
      val piece = new Piece(color, "pawn", col, row)
      pawns = Pawn(piece.name, piece.position) :: pawns
    }
    pawns
  }
}

class Piece (color: String, pieceType: String, col: Char, row: Int) {
  private val conf: Config = ConfigFactory.load("chesspieces.conf")

  def name: String = s"${color.charAt(0).toUpper}${pieceType.charAt(0).toUpper}$col$row"

  def position: String = conf.getString(s"$color.pawn.$name")
}
