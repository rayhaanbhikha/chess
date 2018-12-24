import com.rayhaanbhikha.chess.board.ChessBoard
import com.rayhaanbhikha.chess.pieces.{ChessPiece, Pawn}

import scala.collection.mutable.ArrayBuffer

object Main extends App {

  val pawn: Pawn = Pawn("white", "a2")

  val chessPieces: ArrayBuffer[ChessPiece] = ArrayBuffer(
    pawn,
    pawn.copy(initialPosition = "b2"),
    pawn.copy(initialPosition = "c2")
  )
  val chessBoard = new ChessBoard(chessPieces)

  chessBoard.printBoard()

//  chessBoard.move("wpa2", "a3")

//  chessBoard.printBoard()

}
