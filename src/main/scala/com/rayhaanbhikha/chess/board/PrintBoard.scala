package com.rayhaanbhikha.chess.board

object PrintBoard {
  private val columns: List[Char] = Board.columns
  // print board. - reads chessboard squares map.
  def apply(chessBoardSquares: Map[String, ChessBoardSquare]): Unit = {

    printLabels()
    for {
      row <- Board.rows.reverse
      col <- columns
    } yield {

        if(col == 'a' ) print(s"$row ")
        val position: String = s"$col$row"
        if(chessBoardSquares(position).isEmpty)
          print(s" . ")
        else
          print(s" ${chessBoardSquares(position).chessPiece.utfImage} ")
        if(col == 'h') {
          print(s" $row")
          println()
        }
    }
    printLabels("bottom")

  }

  def printLabels(pos: String = "top") = pos match {
    case "top" => {
      printRow
      printLine
    }
    case "bottom" => {
      printLine
      printRow
    }
  }

  def printRow: Unit = {
    print("  ")
    columns.foreach(col => print(s" $col "))
    println()
  }

  def printLine(): Unit = {
    print("  ")
    columns.foreach(_ => print(s"==="))
    println()
  }
}

