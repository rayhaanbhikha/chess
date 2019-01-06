package com.rayhaanbhikha.chess.board

import com.rayhaanbhikha.chess.pieces.ChessPiece

class ChessBoardSquare(position: String) {
  private var _chessPiece: Option[ChessPiece] = None
  def isEmpty: Boolean = _chessPiece.isEmpty
  def isDefined: Boolean = _chessPiece.isDefined

  def chessPiece: ChessPiece = _chessPiece.get
  def chessPiece_= (newChessPiece: ChessPiece): Unit = {
    _chessPiece = Some(newChessPiece)
  }

  def removeChessPiece(): Unit = {
    _chessPiece = None
  }
}
