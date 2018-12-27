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
//    possibleMoves.foreach(p => println(p.toString))
    val availableMoves: List[AvailableMove] = Translation.convertTranslations(possibleMoves, currentPosition)
//    availableMoves.foreach(a => print(s"\t ${a.position}"))
    availableMoves.flatMap(filterMove(_, chessBoardSquares))
  }

  def filterMove(availableMove: AvailableMove, chessBoardSquares: Map[String, ChessBoardSquare]): Option[String] = {
    val position = availableMove.position
    val chessBoardSquare = chessBoardSquares(position)

    if(chessBoardSquare.isEmpty)
      Some(position)
    else if(chessBoardSquare.chessPiece.color != color)
      Some(position)
    else
      None
  }

  override def movePiece(newPosition: String, chessBoardSquares: Map[String, ChessBoardSquare]): Unit = {
    // 1. move selected piece to new position.
    chessBoardSquares(newPosition).chessPiece = this

    // 2. remove selected piece from it's previous position. (if it exists)
    chessBoardSquares(this.currentPosition).removeChessPiece()

    // 3. update pawns current position
    this.currentPosition = newPosition
  }
}
