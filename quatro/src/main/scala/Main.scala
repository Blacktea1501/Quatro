// Quatro is a board game.
// The game is played on a 4x4 board with 16 squares.
// There are two players with 8 pieces each.
// Each peace is distinct from the others.

// But they have 4 diffrent attributes:
// 1. Color: Red or Blue // hex colored strings
// 2. Shape: Circle or Square // () or []
// 3. Size: Big or Small // (2) for big or (1) for small
// 4. Hole: Hollow or Solid // (()) or ()  for circle and [[]] or [] for square

// Goal is to get 4 pieces in a row that share a common attribute.
// Visiual
//    |-----|-----|-----|-----|
//  4 |((1))|     |     |     |
//    |-----|-----|-----|-----|
//  3 |     |     |     |     |
//    |-----|-----|-----|-----|
//  2 |     |     |     |     |
//    |-----|-----|-----|-----|
//  1 |     |     |     |     |
//    |-----|-----|-----|-----|
//      A     B     C     D

import Piece.Piece
import Board.Board

@main
def main(): Unit =

  // create a new board
  var board = Board()
  var player1 = initPlayer(true) 
  var player2 = initPlayer(false)

  // print the players
  printPlayer(player1)
  printPlayer(player2)
