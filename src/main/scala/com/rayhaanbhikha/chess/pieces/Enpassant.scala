package com.rayhaanbhikha.chess.pieces

case class Enpassant(var isAvailable: Boolean = false) {

  var move: Option[String] = None

  var chessPieceToAttack: Option[ChessPiece] = None

  def remove: Unit = {
    isAvailable = false
    move = None
    chessPieceToAttack = None
  }
}
