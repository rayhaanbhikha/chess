package com.rayhaanbhikha.chess.pieces

import com.rayhaanbhikha.chess.board.Board
import com.typesafe.config.{Config, ConfigFactory}

object ChessPieces {

  private val columns = Board.columns

  def apply(): List[ChessPiece] = {
    whitePieces ::: blackPieces
  }

  def whitePieces: List[ChessPiece] = {
    val color = "white"
    pawns(color, 2) :::
      knights(color, 1) :::
      bishops(color, 1)
  }

  def blackPieces: List[ChessPiece] = {
    val color = "black"
    pawns(color, 7) :::
      knights(color, 8) :::
      bishops(color, 8)
  }

  def bishops(color: String, row: Int): List[Bishop] = {
    var bishops: List[Bishop] = List()
    val columns: List[Char] =  List('c', 'f')
    columns.foreach(col => {
      val piece = new Piece(color, "bishop", col, row)
      bishops = Bishop(piece.name, piece.position) :: bishops
    })
    bishops
  }

  def knights(color: String, row: Int): List[Knight] = {
    var knights: List[Knight] = List()
    val columns: List[Char] =  List('b', 'g')
    columns.foreach(col => {
      val piece = new Piece(color, "knight", col, row)
      knights = Knight(piece.name, piece.position) :: knights
    })
    knights
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

private class Piece (color: String, pieceType: String, col: Char, row: Int) {
  private val conf: Config = ConfigFactory.load("chesspieces.conf")

  def name: String = s"${color.charAt(0).toUpper}${pieceType.charAt(0).toUpper}$col$row"

  def position: String = conf.getString(s"$color.$pieceType.$name")
}
