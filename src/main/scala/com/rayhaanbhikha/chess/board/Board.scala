package com.rayhaanbhikha.chess.board

object Board {
  val columns: List[Char] = List('a','b','c','d','e','f','g','h')
  val rows = Range(1, 9)

  def getNewCol(currentColumn: Char, delta: Int): Char = {
    val currentIndexOfCol = columns.indexWhere(currentColumn == _)
    if( currentIndexOfCol == -1)
      throw new IndexOutOfBoundsException(s"$currentColumn does not exist")

    columns(currentIndexOfCol + delta)
  }

  def getNewRow(currentRow: Int, delta: Int): Int = {
    val newRow = currentRow + delta
    if(newRow > 8 || newRow < 1)
      throw new IndexOutOfBoundsException(s"$newRow in correct ")
    newRow
  }
}

