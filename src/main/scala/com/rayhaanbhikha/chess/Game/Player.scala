package com.rayhaanbhikha.chess.Game

case class Player(color: String, name: String) {
  var score: Int = 0
  var inCheck: Boolean = false
}
