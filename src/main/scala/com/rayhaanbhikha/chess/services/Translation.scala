package com.rayhaanbhikha.chess.services

import com.rayhaanbhikha.chess.board.Board

case class Translation(var x: Int, var y: Int) {
  override def toString: String = s"[$x, $y]"
}

object Translation {

  def convertTranslation(translation: Translation, currentPos: String): Option[AvailableMove] = {
      val col = currentPos.charAt(0)
      val row = currentPos.charAt(1).toInt - 48

      val newCol = Board.getNewCol(col, translation.x)
      val newRow = Board.getNewRow(row, translation.y)

      if(newCol.isDefined && newRow.isDefined) {
        val newPosition = s"${newCol.get}${newRow.get}"
        val availableMove = new AvailableMove(translation, newPosition)
        Some(availableMove)
      }
      else
        None
  }

  def convertTranslations(translations: List[Translation], currentPos: String): List[AvailableMove] = {
    translations.flatMap(convertTranslation(_, currentPos))
  }
}
