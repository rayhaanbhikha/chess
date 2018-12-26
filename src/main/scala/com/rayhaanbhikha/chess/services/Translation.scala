package com.rayhaanbhikha.chess.services

import com.rayhaanbhikha.chess.board.Board

case class Translation(var x: Int, var y: Int) {
  def direction: String = x match {
    case 1 | -1 => "diagonal"
    case _ => "vertical"
  }

  val isDiagonal: () => Boolean = () => direction == "diagonal"
  val isVertical: () => Boolean = () => direction == "vertical"

  def flip(): Unit = {
    x *= -1
    y *= -1
  }

  override def toString: String = s"[$x, $y]"
}

object Translation {
  var chessBoardColumns: List[Char] = Board.columns
  var chessBoardRows = Range(1,9)

  def convertTranslation(translation: Translation, currentPos: String): Option[AvailableMove] = {
    val col = currentPos.charAt(0) // character
    val row = currentPos.charAt(1).toInt - 48 // Int

    try {
      val newColIndex = chessBoardColumns.indexOf(col) + translation.x // Int
      val newCol = chessBoardColumns(newColIndex)
      val newRow = chessBoardRows(row - 1) + translation.y

      val newPosition = s"$newCol$newRow"
      val availableMove = new AvailableMove(translation, newPosition)
       Some(availableMove)
    } catch {
      case _: IndexOutOfBoundsException =>
//        println(s"\n($col, $row) -> [${translation.x}, ${translation.y}]  x")
        None
    }
  }

  /**
    *
    * @param translations
    * @param currentPos
    * @return List[String] - i.e. moves in string format.
    */
  def convertTranslations(translations: List[Translation], currentPos: String): List[AvailableMove] = {
    translations.flatMap(convertTranslation(_, currentPos))
  }

  def prettyPrint(translation: Translation): Unit = {
    print(s"[${translation.x}, ${translation.y}]\t")
  }

  def flip(translations: List[Translation]): List[Translation] = {
      translations.foreach(translation => translation.flip())
      translations
  }
}
