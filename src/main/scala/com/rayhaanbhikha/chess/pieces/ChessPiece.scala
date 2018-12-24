package com.rayhaanbhikha.chess.pieces

import scala.collection.mutable.ArrayBuffer

trait ChessPiece {
    val pieceType: String = this.getClass.getSimpleName.toLowerCase
    val value: Int
    val color: String
    val initialPosition: String

    var currentPosition: String = initialPosition

    def moves: ArrayBuffer[Translation]

    def active: Boolean = {
      if(currentPosition.equals(initialPosition))
        false
      else
        true
    }

    // name = color + pieceType + initialPosition
    def name: String = s"${color.charAt(0)}${pieceType.charAt(0)}$initialPosition"
//    def getAvailableMoves: Boolean
//    def isAvailable: Boolean
//    def makeMove: Boolean
//    def incrementGamePoint(): Unit

}
