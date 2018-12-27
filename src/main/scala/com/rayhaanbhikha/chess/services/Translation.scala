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

  def convertTranslation(translation: Translation, currentPos: String): Option[AvailableMove] = {

    try {
      val col = currentPos.charAt(0)
      val row = currentPos.charAt(1).toInt - 48

      val newCol = Board.getNewCol(col, translation.x)
      val newRow = Board.getNewRow(row, translation.y)

      val newPosition = s"$newCol$newRow"
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
