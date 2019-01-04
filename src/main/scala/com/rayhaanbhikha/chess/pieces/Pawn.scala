package com.rayhaanbhikha.chess.pieces

import com.rayhaanbhikha.chess.Game.Player
import com.rayhaanbhikha.chess.board.ChessBoardSquare
import com.rayhaanbhikha.chess.services.{AvailableMove, Translation}

import scala.util.control.Breaks.{break, breakable}

case class Pawn(override val name: String,
                override var currentPosition: String,
                override val player: Player) extends ChessPiece {

  override val value: Int = 1

  override val utfImage = color match {
    case "white" => "\u2659"
    case "black" => "\u265F"
  }

  def possibleMoves: Map[String, List[Translation]] = {
    val increment = color match {
      case "white" => 1
      case "black" => -1
    }
    
    Map(
      "mNorthEast" -> List(Translation(increment, increment)),
      "mNorthWest" -> List(Translation(-increment, increment)),
      "mNorth" -> (if (!active) List(Translation(0, increment), Translation(0, increment * 2)) else List(Translation(0, increment)))
    )
  }

  override def getAvailableMoves(chessBoardSquares: Map[String, ChessBoardSquare]): List[String] = {

    var returnMoves: List[String] = List()

    for((direction, moves) <- possibleMoves) {
      val availableMoves: List[AvailableMove] = Translation.convertTranslations(moves, currentPosition)
      direction match {
        case "mNorthEast" | "mNorthWest" =>
          returnMoves = filterDiagonal(availableMoves, chessBoardSquares) ::: returnMoves
        case "mNorth" =>
          returnMoves = filterVertical(availableMoves, chessBoardSquares) ::: returnMoves
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
}
