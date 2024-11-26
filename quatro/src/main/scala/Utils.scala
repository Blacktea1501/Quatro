import Board.Board
import Piece.Piece

case class Utils()

def initPlayer(x: Boolean): Array[Piece] = {
  var player: Array[Piece] = Array.ofDim[Piece](8)
    for (i <- 0 to 7) {
      val shape = i % 4 < 2
      val hollow = i % 8 < 4
      val size = i % 2 == 0
      val piece = Piece(x, size, shape, hollow)
      player(i) = piece
    }
  player
}

def printPlayer(player: Array[Piece]): Unit = {
  print("[")
  for (i <- 0 to 7) {
    print(player(i))
    if (i < 7) {
      print(", ")
    }
  }
  println("]")
}
