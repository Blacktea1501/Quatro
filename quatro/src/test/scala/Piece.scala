import Piece.Piece

class PieceTest extends munit.FunSuite {
  test("Testing toString method of Piece") {
    var piece = Piece(true, true, true, true)
    var expected = "\u001b[31m((2))\u001b[0m"
    var s = piece.toString()
    assertEquals(s, expected)

    piece = Piece(false, false, false, false)
    expected = "\u001b[34m [1] \u001b[0m"
    s = piece.toString()
    assertEquals(s, expected)
  }


}
