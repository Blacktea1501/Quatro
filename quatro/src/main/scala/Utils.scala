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
  for (i <- 0 to player.length - 1) {
    print(i + ": " + player(i))
    if (i < player.length - 1) {
      print(", ")
    }
  }
  println("]")
}

def clearScreen(): Unit = {
  print("\u001b[2J")
}

def traslatePosition(s: String): (Int, Int) = {
  val col = s(0) match {
    case 'A' => 0
    case 'B' => 1
    case 'C' => 2
    case 'D' => 3
    case _   => -1
  }

  val row = s(1) match {
    case '1' => 3
    case '2' => 2
    case '3' => 1
    case '4' => 0
    case _   => -1
  }

  (row, col)
}
