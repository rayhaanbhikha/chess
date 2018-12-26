import com.rayhaanbhikha.chess.board.ChessBoard
import com.rayhaanbhikha.chess.pieces.{ChessPiece, Pawn}
import com.rayhaanbhikha.chess.services.Translation

import scala.collection.mutable.ArrayBuffer

object Main extends App {

  val whitePawn: Pawn = Pawn("white", "a2")
  val blackPawn: Pawn = Pawn("black", "a7")


  val chessPieces: ArrayBuffer[ChessPiece] = ArrayBuffer(
    whitePawn,
    whitePawn.copy(initialPosition = "b2"),
    whitePawn.copy(initialPosition = "c2"),
    whitePawn.copy(initialPosition = "b6"),
    blackPawn,
    blackPawn.copy(initialPosition = "b7"),
    blackPawn.copy(initialPosition = "b3"),
  )
  val chessBoard = new ChessBoard(chessPieces)
  chessBoard.printBoard()


  val chessPieceName = "bpb7"
  try {
    // select chess piece

    val availableMoves = chessBoard.select(chessPieceName)
    println("Available Moves: ")
    availableMoves.foreach(move => print(s"$move\t"))

  } catch {
    case _: NoSuchElementException =>
      println(s"$chessPieceName does not exist. Please try again")
  }




  //  chessBoard.move("wpb2", "b4")
//  chessBoard.printBoard()


  //  chessBoard.move("wpc2", "b3")

//  chessBoard.printBoard()

}
