package com.rayhaanbhikha.chess.pieces
import com.rayhaanbhikha.chess.board.{Board, ChessBoardSquare}
import com.rayhaanbhikha.chess.services.{AvailableMove, Translation}

import scala.util.control.Breaks.{break, breakable}

case class Rook(override val name: String, override var currentPosition: String) extends ChessPiece {
  override val value: Int = 5

    override def possibleMoves: Map[String, List[Translation]] = {
    var mNorth: List[Translation] = List()
    var mEast: List[Translation] = List()
    var mSouth: List[Translation] = List()
    var mWest: List[Translation] = List()

      for( row <- Board.rows.reverse) {
        mNorth = Translation(0, row) :: mNorth
        mEast = Translation(row, 0) :: mEast
        mSouth = Translation(0, -row) :: mSouth
        mWest = Translation(-row, 0) :: mWest
      }
    Map(
      "mNorth" -> mNorth,
      "mEast" -> mEast,
      "mSouth" -> mSouth,
      "mWest" -> mWest
    )
  }

  override def getAvailableMoves(chessBoardSquares: Map[String, ChessBoardSquare]): List[String] = {

    var movesToReturn: List[String] = List()

    for ((_, moves) <- possibleMoves) {
      // translations -> availableMoves
      val availableMoves: List[AvailableMove] = Translation.convertTranslations(moves, currentPosition)

      // filter List of available moves.
      val filteredAvailableMoves: List[String] = filterMoves(availableMoves, chessBoardSquares)

      movesToReturn = filteredAvailableMoves ::: movesToReturn
    }

    movesToReturn
  }

  def filterMoves(availableMoves: List[AvailableMove], chessBoardSquares: Map[String, ChessBoardSquare]): List[String] = {
    var filteredMoves: List[String] = List()

    breakable {
      for(move <- availableMoves) {
        val position = move.position
        val chessBoardSquare = chessBoardSquares(position)

        if (chessBoardSquare.isEmpty) {
          filteredMoves = position :: filteredMoves
        }
        // if collide with other color then add position then break.
        else if (!chessBoardSquare.isEmpty && chessBoardSquare.chessPiece.color != color){
          filteredMoves = position :: filteredMoves
          break
        }
        // if collide with same color just break.
        else if(!chessBoardSquare.isEmpty && chessBoardSquare.chessPiece.color == color) {
          break
        }
      }
    }

    filteredMoves
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
