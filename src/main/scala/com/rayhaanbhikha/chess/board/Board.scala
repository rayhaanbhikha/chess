package com.rayhaanbhikha.chess.board

object Board {
  val columns: List[Char] = List('a','b','c','d','e','f','g','h')
  val rows: List[Int] = (1 to 8).toList

  def getNewCol(currentColumn: Char, delta: Int): Option[Char] = {
    try {
      val currentIndexOfCol = columns.indexWhere(currentColumn == _)
      val newCol = columns(currentIndexOfCol + delta)
      Some(newCol)
    } catch {
      case _: Throwable => {
        None
      }
    }
  }

  def getNewRow(currentRow: Int, delta: Int): Option[Int] = {
    try {
      val newRow = rows(currentRow + delta) - 1
      if(newRow < 1 || newRow > 8) throw new IndexOutOfBoundsException
      Some(newRow)
    } catch {
      case _: Throwable => {
        None
      }
    }
  }

  def diagonalSquare(position: String, delta: Int): Option[String] = {
    val col = position.charAt(0)
    val row = position.charAt(1)
    println(col, "-", row)

    val newCol = getNewCol(col, delta)

    if(newCol.isDefined)
      Some(s"${newCol.get}$row")
    else
      None
  }


  def getAdjacentSquares(position: String, direction: Int): List[String] = {
    List(("right", direction), ("left", -direction))
      .flatMap(move => diagonalSquare(position, move._2))
  }
}

