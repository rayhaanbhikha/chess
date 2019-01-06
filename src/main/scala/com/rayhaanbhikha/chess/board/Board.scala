package com.rayhaanbhikha.chess.board

object Board {
  val columns: List[Char] = List('a','b','c','d','e','f','g','h')
  val rows: List[Int] = (1 to 8).toList

  def getNewCol(currentColumn: Char, delta: Int): Char = {
    val currentIndexOfCol = columns.indexWhere(currentColumn == _)
    val newCol = columns(currentIndexOfCol + delta)
    if(currentIndexOfCol == - 1 || newCol == -1)
      throw new IndexOutOfBoundsException(s"$currentColumn does not exist")
    else
      newCol
  }

  def getNewRow(currentRow: Int, delta: Int): Int = {
    val newRow = currentRow + delta
    if(newRow > 8 || newRow < 1)
      throw new IndexOutOfBoundsException(s"$newRow in correct ")
    newRow
  }

  def diagonalSquare(position: String, delta: Int): String = {
    val col = position.charAt(0)
    val row = position.charAt(1)
    val newCol = getNewCol(col, delta)
    s"$newCol$row"
  }

  def deltaRow(currentPosition: String, delta: Int): String = {
    val row = currentPosition.charAt(1)
    val col = currentPosition.charAt(0)

    s"$col${getNewRow(row, delta)}"
  }
}

