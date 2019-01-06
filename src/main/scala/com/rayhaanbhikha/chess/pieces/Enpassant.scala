package com.rayhaanbhikha.chess.pieces

import com.rayhaanbhikha.chess.board.Board
import com.rayhaanbhikha.chess.services.Translation

case class Enpassant(chessPieceToAttack: ChessPiece,
                     pawnColor: String,
                     pawnPosition: String,
                     offendingChessPieceNewPosition: String) {

  def move: String = {
    val m = pawnColor match {
      case "white" => Board.getNewPosition(offendingChessPieceNewPosition, new Translation(0, 1))
      case "black" => Board.getNewPosition(offendingChessPieceNewPosition, new Translation(0, -1))
    }

    m.get // this will never be None as enpassant move is always inbetween.
  }

}
