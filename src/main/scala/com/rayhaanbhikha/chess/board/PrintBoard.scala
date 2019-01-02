package com.rayhaanbhikha.chess.board

object PrintBoard {
  private val columns: List[Char] = Board.columns
  // print board. - reads chessboard squares map.
  def apply(chessBoardSquares: Map[String, ChessBoardSquare]): Unit = {

    printTopRow
    for {
      row <- Board.rows
      col <- columns
    } yield {

        if(col == 'a' ) print(row)
        val position: String = s"$col$row"
        if(chessBoardSquares(position).isEmpty)
          print(s"\t  -  ")
        else
          print(s"\t${chessBoardSquares(position).chessPiece.utfImage}\t")

        if(col == 'h') {
          print(s"\t${row}")
          println()
        }
    }
    printBottomRow
  }

  def printSquareRow: Unit = {
    print("+ - +")
    println()
    print("|   |")
    println()
    print("+ - +")
  }

  def printCorner: Unit = {
    print("+ - \u231D")
    println()
    print("|   |")
    println()
    print("+ - \u231F")
  }

  def printTopRow: Unit = {
    printRow
    printLine
  }
  def printBottomRow: Unit = {
    printLine
    printRow
  }
  def printRow: Unit = {
    print("\t")
    for( col <- columns) {
      print(s"  $col  \t")
    }
    println()
  }

  def printLine(): Unit = {
    for( _ <- columns) {
      print(s"\t=====")
    }
    println()
  }
}

