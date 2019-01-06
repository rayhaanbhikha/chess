package com.rayhaanbhikha.chess.pieces

case class Enpassant(chessPieceToAttack: ChessPiece,
                     color: String,
                     currentPosition: String) {

  def move: String = {
//    color match {
//      case "white" => Board.deltaRow(currentPosition, 1)
//      case "black" => Board.deltaRow(currentPosition, -1)
//    }
    "g6"
  }

}
