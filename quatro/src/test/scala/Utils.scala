import Piece.Piece
import Board.Board

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

}
