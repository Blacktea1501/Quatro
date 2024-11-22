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


final val RED = "\u001b[31m"
final val BLUE = "\u001b[34m"

def printEveryPiece(): Unit = {
  for (i <- 0 to 15) {
    val shape = if i % 4 < 2 then true else false
    val hollow = if i % 8 < 4 then true else false
    val size = if i % 2 == 0 then true else false
    val color = if i < 8 then true else false
    val piece = Piece(color, size, shape, hollow)
    println(piece)
  }
}

def printPieceOnBoard(): Unit = {
  var board = Board()
  for (i <- 0 to 15) {
    val shape = if i % 4 < 2 then true else false
    val hollow = if i % 8 < 4 then true else false
    val size = if i % 2 == 0 then true else false
    val color = if i < 8 then true else false
    val piece = Piece(color, size, shape, hollow)
    board.placePiece(piece, i % 4, i / 4)
  }
  println(board)
}

@main
def main(): Unit =
  printEveryPiece()
  printPieceOnBoard()
