package com.rayhaanbhikha.chess.services

class AvailableMove(val translation: Translation, val position: String)

object AvailableMove {
  def sort(color: String, availableMoves: List[AvailableMove]): List[AvailableMove] = color match {
    case "white" => availableMoves.sortWith(_.translation.y < _.translation.y)
    case "black" => availableMoves.sortWith(_.translation.y > _.translation.y)
  }
}
