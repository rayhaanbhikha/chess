import com.rayhaanbhikha.chess.board.{ChessBoard, PrintBoard}
import com.rayhaanbhikha.chess.pieces.{ChessPiece, ChessPieces}

object Main extends App {

  val chessPieces: List[ChessPiece] = ChessPieces()
  val chessBoard = new ChessBoard(chessPieces)
  val printBoard = () => PrintBoard(chessBoard.chessBoardSquares)

  val wp = "WPb2"
  val bp = "BPa7"


//  printBoard()

//  makeMove(bp, "a6")

//
  availableMoves(bp)

//  makeMove(bp, "a5")
//
  availableMoves(bp)
//
//  makeMove(bp, "b2")

//  availableMoves(bp)

  def makeMove(chessPieceName: String, newPos: String): Unit = {
    chessBoard.move(chessPieceName, newPos)
    printBoard()
  }

  def availableMoves(chessPieceName: String): Unit = {
      val availableMoves = chessBoard.select(chessPieceName)
      printAvailableMoves(availableMoves, chessPieceName)
  }

  def printAvailableMoves(moves: List[String], chessPieceName: String): Unit = {
    println(s"Available Moves: $chessPieceName")
    moves.foreach(move => print(s"$move\t"))
    println("\n========\n")
  }
}
