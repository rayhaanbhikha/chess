import Main.pawn
import com.rayhaanbhikha.chess.board.ChessBoard
import com.rayhaanbhikha.chess.pieces.{ChessPiece, Pawn}

import scala.collection.mutable.ArrayBuffer

object Main extends App {

  val pawn: Pawn = Pawn("white", "a2")

  val chessPieces: ArrayBuffer[ChessPiece] = ArrayBuffer(
    pawn,
    pawn.copy(initialPosition = "b2"),
    pawn.copy(initialPosition = "c2"),
    pawn.copy(initialPosition = "b3")

  )
  val chessBoard = new ChessBoard(chessPieces)

  chessBoard.printBoard()

  chessBoard.select("wpb2")

//  Translation.convertTranslation(new Translation(1, 1), "a2")

//  Translation.convertTranslation(new Translation(1, 1), "h2")






  //  game while loop
  // select chess piece -
  // get available moves
  // make move

  // chessboard is updated and printed.

//  chessBoard.printBoard()

}
