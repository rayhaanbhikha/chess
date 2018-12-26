import com.rayhaanbhikha.chess.board.{ChessBoard, PrintBoard}
import com.rayhaanbhikha.chess.pieces.{ChessPiece, ChessPieces, Pawn}

object Main extends App {

  val chessPieces: List[ChessPiece] = ChessPieces()
  val chessBoard = new ChessBoard(chessPieces)
  val printBoard = () => PrintBoard(chessBoard.chessBoardSquares)

  printBoard()

  val chessPieceNames = List("wpa2", "wpb2", "bpb7")

  chessPieceNames.foreach(playChessPiece)

  def playChessPiece(chessPieceName: String): Unit = {
    try {
      // select chess piece
      val availableMoves = chessBoard.select(chessPieceName)
      printAvailableMoves(availableMoves, chessPieceName)

    } catch {
      case _: NoSuchElementException =>
        println(s"$chessPieceName does not exist. Please try again")
    }
  }

  def printAvailableMoves(moves: List[String], chessPieceName: String): Unit = {
    println(s"Available Moves: $chessPieceName")
    moves.foreach(move => print(s"$move\t"))
    println("\n========\n")
  }
}
