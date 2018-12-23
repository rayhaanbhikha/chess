import com.rayhaanbhikha.chess.chesspieces.Pawn

object Main extends App {
  val whitePawn = new Pawn("white","e1")

  println(whitePawn.name)
  println(whitePawn.active)


  whitePawn.moves.foreach(move => {
    println(move.x, move.y)
  })

  whitePawn.currentPosition = "e2"

  println(whitePawn.currentPosition)

  whitePawn.moves.foreach(move => {
    println(move.x, move.y)
  })
}
