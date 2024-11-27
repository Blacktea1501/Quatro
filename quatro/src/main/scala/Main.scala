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
  clearScreen()

  // create a new board
  var board = Board()
  var player1 = initPlayer(true)
  var player2 = initPlayer(false)

  println("Welcome to Quatro!")
  println("Player 1 is Red and Player 2 is Blue")
  println("Player 1 goes first")
  println(
    "To place a piece, type the position (e.g. A1) and the piece number (e.g. 0)"
  )
  println("To win, get 4 pieces in a row that share a common attribute")

  println()
  println()
  println()

  var gameover = false
  while (!gameover) {
    // print the players
    println(board)
    printPlayer(player1)
    printPlayer(player2)
    player1 = playerMove(board, player1, 1)

    // check win condition 
    for (i <- 0 to 3) {
      if (board.checkRowWin(i) || board.checkColWin(i) || i == 0 && board.checkDiagWin()) {
        println("Player 1 wins!")
        gameover = true
      }
    }
     
    clearScreen()
    println(board)
    printPlayer(player1)
    printPlayer(player2)



    player2 = playerMove(board, player2, 2)
    for (i <- 0 to 3) {
      if (board.checkRowWin(i) || board.checkColWin(i) || i == 0 && board.checkDiagWin()) {
        println("Player 2 wins!")
        gameover = true
      }
    }
    clearScreen()

  }
