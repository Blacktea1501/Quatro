package Board;
import Piece.Piece

class Board() {

  // allocates an empty 4x4 board
  private var _board: Array[Array[Option[Piece]]] =
    Array
      .ofDim[Option[Piece]](4, 4)
      .map(_ => Array.ofDim[Option[Piece]](4).map(_ => None))

  /**
    *
    *
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


  def checkRowWin(_row: Int): Boolean = {
    if (_row < 0 || _row > 3) {
      return false
    }
    // filter all the None values out of the row
    val row = this._board(_row).filter(_.isDefined).map(_.get);

    if (row.length < 4) {
      return false
    }

    return row.map(_.getColorBoolean()).toSet.size == 1 ||
      row.map(_.getShapeBoolean()).toSet.size == 1 ||
      row.map(_.getHollowBoolean()).toSet.size == 1 ||
      row.map(_.getSizeBoolean()).toSet.size == 1
  }

  def checkColWin(_col: Int): Boolean = {
    if (_col < 0 || _col > 3) {
      return false
    }
    // filter all the None values out of the column
    val col = this._board.map(_(_col)).filter(_.isDefined).map(_.get);

    if (col.length < 4) {
      return false
    }

    return col.map(_.getColorBoolean()).toSet.size == 1 ||
      col.map(_.getShapeBoolean()).toSet.size == 1 ||
      col.map(_.getHollowBoolean()).toSet.size == 1 ||
      col.map(_.getSizeBoolean()).toSet.size == 1
  }

  def checkDiagWin(): Boolean = {
    val diag1 = Array(
      this._board(0)(0),
      this._board(1)(1),
      this._board(2)(2),
      this._board(3)(3)
    ).filter(_.isDefined).map(_.get);

    val diag2 = Array(
      this._board(0)(3),
      this._board(1)(2),
      this._board(2)(1),
      this._board(3)(0)
    ).filter(_.isDefined).map(_.get);

    if (diag1.length < 4 && diag2.length < 4) {
      return false;
    }

    return diag1.map(_.getColorBoolean()).toSet.size == 1 ||
      diag1.map(_.getShapeBoolean()).toSet.size == 1 ||
      diag1.map(_.getHollowBoolean()).toSet.size == 1 ||
      diag1.map(_.getSizeBoolean()).toSet.size == 1 ||
      diag2.map(_.getColorBoolean()).toSet.size == 1 ||
      diag2.map(_.getShapeBoolean()).toSet.size == 1 ||
      diag2.map(_.getHollowBoolean()).toSet.size == 1 ||
      diag2.map(_.getSizeBoolean()).toSet.size == 1
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
