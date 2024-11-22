import Board.Board

class MyBoardTest extends munit.FunSuite {
  test("Testing toString method of Board on empty board") {
    val expected ="""  |-----|-----|-----|-----|
4 |     |     |     |     |
  |-----|-----|-----|-----|
3 |     |     |     |     |
  |-----|-----|-----|-----|
2 |     |     |     |     |
  |-----|-----|-----|-----|
1 |     |     |     |     |
  |-----|-----|-----|-----|
     A     B     C     D"""
    val board = Board()
    val s = board.toString()

    assertEquals(s, expected)
  }

}
