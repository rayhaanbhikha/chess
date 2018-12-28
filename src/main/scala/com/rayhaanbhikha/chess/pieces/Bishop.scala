package com.rayhaanbhikha.chess.pieces
import com.rayhaanbhikha.chess.board.{Board, ChessBoardSquare}
import com.rayhaanbhikha.chess.services.{AvailableMove, Translation}
import util.control.Breaks._


case class Bishop(override val name: String, override var currentPosition: String) extends ChessPiece {
  override val value: Int = 3

  override def possibleMoves: Map[String, List[Translation]] = {
    var mNorthEast: List[Translation] = List()
    var mNorthWest: List[Translation] = List()
    var mSouthWest: List[Translation] = List()
    var mSouthEast: List[Translation] = List()

      for( row <- Board.rows.reverse) {
        mNorthEast = Translation(row, row) :: mNorthEast
        mSouthEast = Translation(row, -row) :: mSouthEast
        mSouthWest = Translation(-row, -row) :: mSouthWest
        mNorthWest = Translation(-row, row) :: mNorthWest
      }
    Map(
      "mNorthEast" -> mNorthEast,
      "mSouthEast" -> mSouthEast,
      "mSouthWest" -> mSouthWest,
      "mNorthWest" -> mNorthWest
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
