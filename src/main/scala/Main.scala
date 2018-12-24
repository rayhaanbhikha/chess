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

  chessBoard.initialiseChessBoard()
  chessBoard.printBoard()




  val selectPiece = chessPieces.find(chessPiece => chessPiece.name == "wpa2").get

//  selectPiece.movePieceTo("a3")

  selectPiece.currentPosition = "a3"
  chessBoard.printBoard()





}
