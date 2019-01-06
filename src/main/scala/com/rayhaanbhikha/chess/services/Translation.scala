package com.rayhaanbhikha.chess.services

import com.rayhaanbhikha.chess.board.Board

case class Translation(var x: Int, var y: Int) {
  override def toString: String = s"[$x, $y]"
}

object Translation {

  def convertTranslation(translation: Translation, currentPos: String): Option[AvailableMove] = {
      val newPosition = Board.getNewPosition(currentPos, translation)

      if(newPosition.isDefined) {
        val availableMove = new AvailableMove(translation, newPosition.get)
        Some(availableMove)
      }
      else
        None
  }

  def convertTranslations(translations: List[Translation], currentPos: String): List[AvailableMove] = {
    translations.flatMap(convertTranslation(_, currentPos))
  }
}
