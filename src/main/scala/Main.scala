import com.rayhaanbhikha.chess.board.{ChessBoard, PrintBoard}
import com.rayhaanbhikha.chess.pieces.{ChessPiece, ChessPieces, Pawn}

object Main extends App {

  val chessPieces: List[ChessPiece] = ChessPieces()
  val chessBoard = new ChessBoard(chessPieces)
  val printBoard = () => PrintBoard(chessBoard.chessBoardSquares)

  printBoard()

  val chessPieceName = "wpa2"
  try {
    // select chess piece
    val availableMoves = chessBoard.select(chessPieceName)
    println("Available Moves: ")
    availableMoves.foreach(move => print(s"$move\t"))
    chessBoard.move(chessPieceName, "a3")
    printBoard()
  } catch {
    case _: NoSuchElementException =>
      println(s"$chessPieceName does not exist. Please try again")
  }
}
