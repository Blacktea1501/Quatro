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
import scala.util.boundary

@main
def main(): Unit =
  clearScreen()
  // create a new board
  var board = Board()
  var player1 = initPlayer(true)
  var player2 = initPlayer(false)

  println("""Welcome to Quatro!
    |Player 1 is Red and Player 2 is Blue
    |Player 1 goes first
    |To place a piece, type the position (e.g. A1) and the piece number (e.g. 0)
    |To win, get 4 pieces in a row that share a common attribute""".stripMargin)

  var gameover = false
  var turn = false
  while (!gameover) {
    // print the players
    println(board)
    printPlayer(player1)
    printPlayer(player2)

    turn match {
      case false => player1 = playerMove(board, player1, 1)
      case true  => player2 = playerMove(board, player2, 2)
    }

    // switch turns
    turn = !turn

    clearScreen()

    for (i <- 0 to 3 if !gameover) {
      gameover = checkWin(board, i, false) | checkWin( board, i, true) | (i == 1 && checkDiagWin(board))
    }

    if (gameover) {
      println(board)
      printPlayer(player1)
      printPlayer(player2)
      println("Player " + (if (turn) 2 else 1) + " wins!")
    }
  }
