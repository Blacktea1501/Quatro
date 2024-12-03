import Piece.Piece
import Board.Board
import Utils._

class UtilsTest extends munit.FunSuite {
  test("Testing winConditions") {
    var board = Board()
    // place winable condition on the board
    val piece = Piece(true, false, false, false)
    val piece1 = Piece(true, false, true, false)
    val piece2 = Piece(true, false, false, true)
    val piece3 = Piece(true, false, true, true)

    val list = List(piece, piece1, piece2, piece3)

    for (i <- 0 to 3) {
      // for row
      board.placePiece(list(i), 0, i)
    }


    // for row
    assert(checkWin(board, 0, false))

    board = Board()
    for (i <- 0 to 3) {
      board.placePiece(list(i), i, 0)
    }

    // for column
    assert(checkWin(board, 0, true))
    
    // first diagonal
    board = Board()
    for (i <- 0 to 3) {
      board.placePiece(list(i), i, i)
    }
    assert(checkDiagWin(board))

    // second diagonal
    board = Board()
    for (i <- 0 to 3) {
      board.placePiece(list(i), i, 3 - i)
    }
    assert(checkDiagWin(board))
  }

  test("Testing translatePosition") {
    assertEquals(translatePosition("A1"), (3, 0))
    assertEquals(translatePosition("B2"), (2, 1))
    assertEquals(translatePosition("C3"), (1, 2))
    assertEquals(translatePosition("D4"), (0, 3))
    assertEquals(translatePosition("g4"), (0, -1))
    assertEquals(translatePosition("A5"), (-1, 0))
  }

  // test("")

}
