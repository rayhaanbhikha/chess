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

  def apply(player: Player): List[ChessPiece] = {
    val color = player.color
    val pawnRow = color match {
      case "white" => 2
      case "black" => 7
    }

    // returns list of chess pieces.
    pawns(color, pawnRow, player) :::
      chessPieces(color, player, "knight" ) :::
      chessPieces(color, player, "bishop") :::
      chessPieces(color, player, "rook")
  }

  def chessPieces(color: String, player: Player, pieceType: String): List[ChessPiece] = {
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
        case "knight" => Knight(piece.name, piece.position, player)
        case "bishop" => Bishop(piece.name, piece.position, player)
        case "rook" => Rook(piece.name, piece.position, player)
      }

      chessPieces = chessPiece :: chessPieces
    })
    chessPieces
  }


  def pawns(color: String, row: Int, player: Player): List[Pawn] = {
    var pawns: List[Pawn] = List()
    for(col <- columns) {
      val piece = new Piece(color, "pawn", col, row)
      pawns = Pawn(piece.name, piece.position, player) :: pawns
    }
    pawns
  }
}

private class Piece (color: String, pieceType: String, col: Char, row: Int) {
  private val conf: Config = ConfigFactory.load("chesspieces.conf")

  def name: String = s"${color.charAt(0).toUpper}${pieceType.charAt(0).toUpper}$col$row"

  def position: String = conf.getString(s"$color.$pieceType.$name")
}
