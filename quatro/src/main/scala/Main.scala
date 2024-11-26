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

  var gameover = false

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

  while (!gameover) {
    // print the players
    println(board)
    printPlayer(player1)
    printPlayer(player2)
    
    // get the player's move
    println("Player 1, place a piece")
    var input = scala.io.StdIn.readLine()
    var (row, col) = traslatePosition(input.substring(0, 2))
    var piece = player1(input.substring(2).trim().toInt)
    board.placePiece(piece, row, col)
    player1 = player1.filter(_ != piece)

    // check if player 1 won
    if (board.checkRowWin(row) || board.checkColWin(col) || board.checkDiagWin()) {
      clearScreen()
      println(board)
      println("Player 1 wins!")
      return
    }

    // print the players
    clearScreen()
    println(board)
    printPlayer(player1)
    printPlayer(player2)
    
    // get the player's move
    println("Player 2, place a piece")
    input = scala.io.StdIn.readLine()
    var (row2, col2) = traslatePosition(input.substring(0, 2))
    piece = player2(input.substring(2).trim().toInt)
    board.placePiece(piece, row2, col2)

    player2 = player2.filter(_ != piece)

    // check if player 2 won
    if (board.checkRowWin(row2) || board.checkColWin(col2) || board.checkDiagWin()) {
      clearScreen()
      println(board)
      println("Player 2 wins!")
      return
    }
    clearScreen()
  }
