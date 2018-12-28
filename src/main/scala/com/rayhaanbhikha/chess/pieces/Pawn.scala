package com.rayhaanbhikha.chess.pieces

import com.rayhaanbhikha.chess.Game.Player
import com.rayhaanbhikha.chess.board.ChessBoardSquare
import com.rayhaanbhikha.chess.services.{AvailableMove, Translation}

import scala.util.control.Breaks.{break, breakable}

case class Pawn(override val name: String,
                override var currentPosition: String,
                override val player: Player) extends ChessPiece {

  override val value: Int = 1

  def possibleMoves: Map[String, List[Translation]] = {
    var mNorthEast: List[Translation] = List()
    var mNorth: List[Translation] = List()
    var mNorthWest: List[Translation] = List()

    color match {
      case "white" =>
        mNorth = if(!active) List(Translation(0,1), Translation(0, 2)) ::: mNorth else Translation(0,1) :: mNorth
        mNorthEast = Translation(1, 1) :: mNorthEast
        mNorthWest = Translation(-1, 1) :: mNorthWest
      case "black" =>
        mNorth = if(!active) List(Translation(0,-1), Translation(0, -2)) ::: mNorth else Translation(0, -1) :: mNorth
        mNorthEast = Translation(-1, -1) :: mNorthEast
        mNorthWest = Translation(1, -1) :: mNorthWest
    }
    Map(
      "mNorthEast" -> mNorthEast,
      "mNorth" -> mNorth,
      "mNorthWest" -> mNorthWest
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
