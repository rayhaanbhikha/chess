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
    Pawn("black", initialPosition = "a3")

  )
  val chessBoard = new ChessBoard(chessPieces)

  chessBoard.printBoard()



  // select chess piece

  chessBoard.select("wpb2")
  chessBoard.move("wpb2", "b4")
  chessBoard.printBoard()


  //  chessBoard.move("wpc2", "b3")

//  chessBoard.printBoard()


  //  Translation.convertTranslation(new Translation(1, 1), "a2")

//  Translation.convertTranslation(new Translation(1, 1), "h2")






  //  game while loop
  // select chess piece -
  // get available moves
  // make move

  // chessboard is updated and printed.

//  chessBoard.printBoard()

}
