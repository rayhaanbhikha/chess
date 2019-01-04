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

  val enpassant = Enpassant()

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
    println(this.enpassant.isAvailable)
    if(this.enpassant.isAvailable) {
      println("chesspiece to kill: ", this.enpassant.chessPieceToAttack.get.name)
      println("Place to move: ", this.enpassant.move.get)
      returnMoves = this.enpassant.move.get :: returnMoves
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
      adjacentChessPiece(newPosition, "right", chessBoardSquares)
      adjacentChessPiece(newPosition, "left", chessBoardSquares)
    }




    // 1. move selected piece to new position.
    chessBoardSquares(newPosition).chessPiece = this

    // 2. remove selected piece from it's previous position. (if it exists)
    chessBoardSquares(this.currentPosition).removeChessPiece()

    // 3. update pawns current position
    this.currentPosition = newPosition

    // 4. check if peice has enpassant. if it does remove it.
    this.enpassant.remove
  }

  def adjacentChessPiece(position: String, adjacentDirection: String, chessBoardSquares: Map[String, ChessBoardSquare]): Unit = {
    val positionOfInterest = adjacentDirection match {
      case "right" => Board.diagonalSquare(position, direction)
      case "left" => Board.diagonalSquare(position, -direction)
    }


    val chessBoardSquare = chessBoardSquares(positionOfInterest)

    if(!chessBoardSquare.isEmpty &&
        chessBoardSquare.chessPiece.pieceType == "pawn"
      ) {

      val pawn = chessBoardSquare.chessPiece.asInstanceOf[Pawn]

      pawn.enpassant.isAvailable = true
      pawn.enpassant.move = Some(currentPosition)
      pawn.enpassant.chessPieceToAttack = Some(this)


      println(chessBoardSquare.chessPiece.name)
    }


  }




  def movedUpTwo(currentPos: String, newPos: String): Boolean = {
    val currentRow = currentPos.charAt(1)
    val newRow = newPos.charAt(1)

    Math.abs(currentRow - newRow) == 2
  }
}
