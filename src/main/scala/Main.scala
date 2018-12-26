import com.rayhaanbhikha.chess.board.ChessBoard
import com.rayhaanbhikha.chess.pieces.{ChessPiece, Pawn}
import com.rayhaanbhikha.chess.services.Translation

import scala.collection.mutable.ArrayBuffer

object Main extends App {

  val pawn: Pawn = Pawn("white", "a2")

  val chessPieces: ArrayBuffer[ChessPiece] = ArrayBuffer(
    pawn,
    pawn.copy(initialPosition = "b2"),
    pawn.copy(initialPosition = "c2"),
    Pawn("black", initialPosition = "b7"),
    pawn.copy(initialPosition = "b6")

  )
  val chessBoard = new ChessBoard(chessPieces)

  chessBoard.printBoard()



  // select chess piece

  chessBoard.select("wpa2")

  chessBoard.select("bpb7")
//  chessBoard.move("wpb2", "b4")
//  chessBoard.printBoard()


  //  chessBoard.move("wpc2", "b3")

//  chessBoard.printBoard()

}
