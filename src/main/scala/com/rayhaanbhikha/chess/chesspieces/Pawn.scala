package com.rayhaanbhikha.chess.chesspieces

import scala.collection.mutable.ArrayBuffer

class Pawn(val color: String,
           val initialPosition: String) extends ChessPiece {

  override val value: Int = 1

  def moves: ArrayBuffer[Translation] = {
    if(active) {
      ArrayBuffer[Translation](
        new Translation(0,1),
        new Translation(1,1),
        new Translation(-1,1)
      )
    } else {
      ArrayBuffer[Translation](
        new Translation(0,1),
        new Translation(0,2),
        new Translation(1,1),
        new Translation(-1,1)
      )
    }
  }








//  override def getAvailableMoves: Boolean = ???
//
//  override def isAvailable: Boolean = ???
//
//  override def makeMove: Boolean = ???
//
//  override def incrementGamePoint(): Unit = ???
}
