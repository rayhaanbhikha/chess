import com.rayhaanbhikha.chess.Game.{ChessPieces, Player}
import com.rayhaanbhikha.chess.board.{ChessBoard, PrintBoard}

object Main extends App {

  // generate players
  val player1 = Player("white", "Ray")
  val player2 = Player("black", "Riz")

  // check color here for each player first. throw exception.

  // generate chess pieces
  val whiteChessPieces = ChessPieces(player1)
  val blackChessPieces = ChessPieces(player2)
  val s = "s"

  val chessBoard = new ChessBoard(whiteChessPieces ::: blackChessPieces)
  val printBoard = () => PrintBoard(chessBoard.chessBoardSquares)

  printBoard()

  availableMoves("WPc2")

//  makeMove("WPc2", "c3")

//  availableMoves("WPc2")


  //  availableMoves("BPf7")
//
//  makeMove("WBc1", "b2")
//
//
//  availableMoves("WBc1")


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
