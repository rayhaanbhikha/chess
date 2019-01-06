package com.rayhaanbhikha.chess.pieces

import com.rayhaanbhikha.chess.board.{Board, ChessBoardSquare}
import com.rayhaanbhikha.chess.services.{AvailableMove, Translation}

import scala.util.control.Breaks.{break, breakable}

case class Pawn(override val name: String, override var currentPosition: String) extends ChessPiece {

  override val value: Int = 1
  override val utfImage = color match {
    case "white" => "\u2659"
    case "black" => "\u265F"
  }

  var enpassant: Option[Enpassant] = None

  val direction = color match {
    case "white" => 1
    case "black" => -1
  }

  def possibleMoves: Map[String, List[Translation]] = {
    Map(
      "mNorthEast" -> List(Translation(direction, direction)),
      "mNorthWest" -> List(Translation(-direction, direction)),
      "mNorth" -> (if (!active) List(Translation(0, direction), Translation(0, direction * 2)) else List(Translation(0, direction)))
    )
  }

  override def getAvailableMoves(chessBoardSquares: Map[String, ChessBoardSquare]): List[String] = {

    var returnMoves: List[String] = List()


    // check if enpassant move is available.
    if(this.enpassant.isDefined) {
      returnMoves = this.enpassant.get.move :: returnMoves
    }


    for((direction, moves) <- possibleMoves) {

      breakable {
        val availableMoves: List[AvailableMove] = Translation.convertTranslations(moves, currentPosition)
        if(availableMoves.isEmpty) break
        direction match {
          case "mNorthEast" | "mNorthWest" =>
            returnMoves = filterDiagonal(availableMoves, chessBoardSquares) ::: returnMoves
          case "mNorth" =>
            returnMoves = filterVertical(availableMoves, chessBoardSquares) ::: returnMoves
        }
      }
    }

    returnMoves
  }


  def filterVertical(availableMoves: List[AvailableMove], chessBoardSquares: Map[String, ChessBoardSquare]): List[String] = {
    var filteredMoves: List[String] = List()

    breakable {
      for(move <- availableMoves) {
        val position = move.position
        val chessBoardSquare = chessBoardSquares(position)

        if (!chessBoardSquare.isEmpty)
          break
        else
          filteredMoves = position :: filteredMoves
      }
    }
    filteredMoves
  }

  def filterDiagonal(availableMoves: List[AvailableMove], chessBoardSquares: Map[String, ChessBoardSquare]): List[String] = {
    // pawn diagonal moves are only ever 1.
    val availableMove = availableMoves.head
    val position = availableMove.position
    val chessBoardSquare = chessBoardSquares(position)

    if(
      !chessBoardSquare.isEmpty &&
        chessBoardSquare.chessPiece.color != color // not same color
    )
      List(availableMove.position)
    else
      List()
  }

  override def movePiece(newPosition: String, chessBoardSquares: Map[String, ChessBoardSquare]): Unit = {

    // check enpassant move here.
    if(movedUpTwo(this.currentPosition, newPosition)) {
      // find adjacent chess pieces.
      adjacentChessPiece(newPosition, chessBoardSquares)
    }




    // 1. move selected piece to new position.
    chessBoardSquares(newPosition).chessPiece = this

    // 2. remove selected piece from it's previous position. (if it exists)
    chessBoardSquares(this.currentPosition).removeChessPiece()


    // 3. update pawns current position
    this.currentPosition = newPosition

    /* intermediate step.
    *   check if enpassant exists on current pawn.
    *
    *   if true check if move played matches move in enpassant instance.
    *
    *   if true, the move is an enpassant move and therefore offending chess piece needs to
    *   be removed from it chess board square as well.
    * */
    if(this.enpassant.isDefined && this.enpassant.get.move == currentPosition) {
      val offendingChessPiecePosition = this.enpassant.get.chessPieceToAttack.currentPosition
      chessBoardSquares(offendingChessPiecePosition).removeChessPiece()
    }

    // 4. check if peice has enpassant. if it does remove it.
    this.enpassant = None
  }

  def adjacentChessPiece(newPosition: String, chessBoardSquares: Map[String, ChessBoardSquare]): Unit = {
    val adjacentSquares = Board.getAdjacentSquares(newPosition, direction)

    adjacentSquares.foreach(position => {
      val chessBoardSquare = chessBoardSquares(position)

      if(chessBoardSquare.isDefined && chessBoardSquare.chessPiece.pieceType == "pawn") {
        val pawn = chessBoardSquare.chessPiece.asInstanceOf[Pawn]
        val enpassant = Enpassant(
          chessPieceToAttack = this,
          pawnColor = pawn.color,
          pawnPosition = pawn.currentPosition,
          offendingChessPieceNewPosition = newPosition
        )

        pawn.enpassant = Some(enpassant)

        pawn.enpassant.get.move
      }
    })
  }




  def movedUpTwo(currentPos: String, newPos: String): Boolean = {
    val currentRow = currentPos.charAt(1)
    val newRow = newPos.charAt(1)

    Math.abs(currentRow - newRow) == 2
  }
}
