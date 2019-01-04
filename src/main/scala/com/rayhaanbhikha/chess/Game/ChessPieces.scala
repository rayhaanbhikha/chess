package com.rayhaanbhikha.chess.Game

import com.rayhaanbhikha.chess.board.Board
import com.rayhaanbhikha.chess.pieces._
import com.typesafe.config.{Config, ConfigFactory}

/*
* Singleton object retrieves chesspieces configuration from chesspiece.conf
* (which holds each chess pieces initial position)
* and returns a list of chesspieces.
*
* */

object ChessPieces {

  private val columns = Board.columns

  def apply(color: String): List[ChessPiece] = {
    val pawnRow = color match {
      case "white" => 2
      case "black" => 7
    }

    // returns list of chess pieces.
    pawns(color, pawnRow) :::
      chessPieces(color, "knight" ) :::
      chessPieces(color, "bishop") :::
      chessPieces(color, "rook")
  }

  def chessPieces(color: String, pieceType: String): List[ChessPiece] = {
    var chessPieces: List[ChessPiece] = List()

    val columns: List[Char] = pieceType match {
      case "knight" => List('b', 'g')
      case "bishop" => List('c','f')
      case "rook" => List('a','h')
    }

    val row: Int = color match {
      case "white" => 1
      case "black" => 8
    }


    columns.foreach(col => {
      val piece = new Piece(color, pieceType, col, row)
      val chessPiece: ChessPiece = pieceType match {
        case "knight" => Knight(piece.name, piece.position)
        case "bishop" => Bishop(piece.name, piece.position)
        case "rook" => Rook(piece.name, piece.position)
      }

      chessPieces = chessPiece :: chessPieces
    })
    chessPieces
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
