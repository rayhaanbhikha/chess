package com.rayhaanbhikha.chess.pieces

import scala.collection.mutable.ArrayBuffer

case class Pawn(color: String, initialPosition: String) extends ChessPiece {

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
