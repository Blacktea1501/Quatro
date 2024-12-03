package Player

import Piece.Piece

class Player(val isRed: Boolean) {


  var pieces: Array[Piece] = Array.ofDim[Piece](8)


  for (i <- 0 to 7) {
    val shape = i % 4 < 2
    val hollow = i % 8 < 4
    val size = i % 2 == 0
    val piece = Piece(isRed, size, shape, hollow)
    pieces(i) = piece
  }

  def getPlayer(): Array[Piece] = { this.pieces }

  def setPlayer(pieces: Array[Piece]): Unit = { this.pieces = pieces }
  
  override def toString: String = {
    var str = "["
    for (i <- 0 to pieces.length - 1) {
      str += i + ": " + pieces(i)
      if (i < pieces.length - 1) {
        str += ", "
      }
    }
    str += "]"
    str
  }
}

