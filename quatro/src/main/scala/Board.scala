package Board;
import Piece.Piece

class Board() {

  // allocates an empty 4x4 board
  private var _board: Array[Array[Option[Piece]]] =
    Array
      .ofDim[Option[Piece]](4, 4)
      .map(_ => Array.ofDim[Option[Piece]](4).map(_ => None))

  /**
    * @param piece the piece to place
    * @param row the row to place the piece
    * @param col the column to place the piece
    * @return true if the piece was placed, false otherwise
    */
  def placePiece(piece: Piece, row: Int, col: Int): Boolean = {

    if (row < 0 || row > 3 || col < 0 || col > 3) {
      println("Invalid position");
      return false
    }

    if (this._board(row)(col).isDefined) {
      println("Spot is already taken");
      return false  
    }

    this._board(row)(col) = Some(piece)
    true
  }



  def getBoard(): Array[Array[Option[Piece]]] = {
    this._board
  }

  def rotate90(): Board = {
    val newBoard = new Board() 
    for (i <- 0 to 3) {
      for (j <- 0 to 3) {
        newBoard.getBoard()(i)(j) = this._board(3 - j)(i)
      }
    }
    newBoard
  }


  override def toString(): String = {
    var s = "  |-----|-----|-----|-----|"
    for (i <- 0 to 3) {
      s += s"\n${4 - i} |"
      for (j <- 0 to 3) {
        this._board(i)(j) match {
          case Some(piece) => s += piece.toString() + "|"
          case None        => s += "     |"
        }
      }
      s += "\n  |-----|-----|-----|-----|"
    }
    s += "\n     A     B     C     D"
    s
  }
}
