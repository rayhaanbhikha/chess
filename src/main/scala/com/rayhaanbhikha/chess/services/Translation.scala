package com.rayhaanbhikha.chess.services

import com.rayhaanbhikha.chess.board.Board

case class Translation(var x: Int, var y: Int) {
  def direction: String = x match {
    case 1 | -1 => "diagonal"
    case _ => "vertical"
  }

  val isDiagonal: () => Boolean = () => direction == "diagonal"
  val isVertical: () => Boolean = () => direction == "vertical"

  override def toString: String = s"[$x, $y]"
}

object Translation {
  var chessBoardColumns: List[Char] = Board.columns
  var chessBoardRows = Range(0,8)

  def convertTranslation(translation: Translation, currentPos: String): Option[AvailableMove] = {

    val col = currentPos.charAt(0) // a
    val row = currentPos.charAt(1).toInt - 48 // 2

    try {
      val newColIndex = chessBoardColumns.indexOf(col + translation.x) // Int
      val newRowIndex = chessBoardRows.indexOf(row + translation.y)

      if(newColIndex == -1 || newRowIndex == -1 ) throw new IndexOutOfBoundsException("hello")

      val newCol = chessBoardColumns(newColIndex)
      val newRow = chessBoardRows(newRowIndex)

      val newPosition = s"$newCol$newRow"
      println(newPosition)
      val availableMove = new AvailableMove(translation, newPosition)
       Some(availableMove)
    } catch {
      case _: IndexOutOfBoundsException =>
        None
    }
  }

  def convertTranslations(translations: List[Translation], currentPos: String): List[AvailableMove] = {
    translations.flatMap(convertTranslation(_, currentPos))
  }

  def flipSign(translation: Translation): Translation = {
    translation.x *= -1
    translation.y *= -1
    translation
  }
}
