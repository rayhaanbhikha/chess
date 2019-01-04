import com.rayhaanbhikha.chess.Game.Player
import com.rayhaanbhikha.chess.board.{ChessBoard, PrintBoard}

object Main extends App {

  // generate players
  val player1 = Player("white", "Ray")
  val player2 = Player("black", "Riz")

  // check color here for each player first. throw exception.

  val chessBoard = new ChessBoard()
  val printBoard = () => PrintBoard(chessBoard.chessBoardSquares)

  printBoard()


//  makeMove("WPb2", "b4")
//
//  availableMoves("BPc7")
//
//
//  makeMove("BPc7", "b2")
//
//  availableMoves("BPc7")
//
//  makeMove("WPf2", "f4")
//
//  availableMoves("BPe7")
//
//  makeMove("BPg7", "g5")
//
//  availableMoves("WPa2")
//
  makeMove("BPg7", "g5")

  availableMoves("WPh2")

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
