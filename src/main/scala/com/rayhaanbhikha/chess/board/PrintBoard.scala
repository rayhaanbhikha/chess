package com.rayhaanbhikha.chess.board

object PrintBoard {
  var columns: Array[Char] = Array('a','b','c','d','e','f','g','h')
  // print board. - reads chessboard squares map.
  def apply(chessBoardSquares: Map[String, ChessBoardSquare]): Unit = {
    printTopRow()
    for(row <- columns.indices.reverse) {
      print(row+1)
      for(col <- columns) {
        val position: String = s"$col${row+1}"
        if(chessBoardSquares(position).isEmpty)
          print(s"\t00000")
        else
          print(s"\t${chessBoardSquares(position).chessPiece.name} ")
      }
      print(s"\t${row+1}")
      println()
    }
    printBottomRow()
  }

  def printTopRow(): Unit = {
    printRow()
    printLine()
  }
  def printBottomRow(): Unit = {
    printLine()
    printRow()
  }
  def printRow(): Unit = {
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

