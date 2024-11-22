package Board;
import Piece.Piece

class Board() {

  private var _board: Array[Array[Option[Piece]]] = {
    val board = Array.ofDim[Option[Piece]](4, 4)
    for (i <- 0 to 3) {
      for (j <- 0 to 3) {
        board(i)(j) = None
      }
    }
    board
  }

  def placePiece(piece: Piece, x: Int, y: Int): Unit = {
    // TODO: check if the spot is empty
    this._board(y)(x) = Some(piece)
  }

  override def toString(): String = {
    var s = "  |-----|-----|-----|-----|"
    for (i <- 0 to 3) {
      s += s"\n${4 - i} |"
      for (j <- 0 to 3) {
        this._board(i)(j) match {
              case Some(piece) => s += piece.toString() + "|"
              case None => s += "     |"
        }
      }
      s += "\n  |-----|-----|-----|-----|"
    }
    s += "\n     A     B     C     D"
    s
  }
}
