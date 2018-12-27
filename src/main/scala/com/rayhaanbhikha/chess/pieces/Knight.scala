package com.rayhaanbhikha.chess.pieces
import com.rayhaanbhikha.chess.board.ChessBoardSquare
import com.rayhaanbhikha.chess.services.{AvailableMove, Translation}

case class Knight(override val name: String, override var currentPosition: String) extends ChessPiece {
  override val value: Int = 3
  override def possibleMoves: List[Translation] = {
    List(
      Translation(-1, 2),
      Translation(1, 2),
      Translation(2, 1),
      Translation(2, -1),
      Translation(1, -2),
      Translation(-1, -2),
      Translation(-2, 1),
      Translation(-2, -1)
    )
  }

  override def getAvailableMoves(chessBoardSquares: Map[String, ChessBoardSquare]): List[String] = {
    val availableMoves: List[AvailableMove] = Translation.convertTranslations(possibleMoves, currentPosition)
//    availableMoves.foreach(a => println(s"\t${a.translation.toString}"))
    List("a")
  }

  def filterMove(availableMove: AvailableMove, chessBoardSquares: Map[String, ChessBoardSquare]) = {
    val position = availableMove.position
    val chessBoardSquare = chessBoardSquares(position)



  }

  override def movePiece(newPosition: String, chessBoardSquares: Map[String, ChessBoardSquare]): Unit = {}
}
