package Board;
import Piece.Piece

class Board() {

  // allocates an empty 4x4 board
  private var _board: Array[Array[Option[Piece]]] =
    Array
      .ofDim[Option[Piece]](4, 4)
      .map(_ => Array.ofDim[Option[Piece]](4).map(_ => None))

  def placePiece(piece: Piece, row: Int, col: Int): Unit = {

    if (row < 0 || row > 3 || col < 0 || col > 3) {
      // reprint the board and print an error message
      println(this.toString());
      println("Invalid row or column");
      return
    }

    if (this._board(row)(col).isDefined) {
      println(this.toString());
      println("Spot is already taken");
      return
    }
    this._board(row)(col) = Some(piece)

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

    if (
      diag1.map(_.getColorBoolean()).toSet.size == 1 ||
      diag1.map(_.getShapeBoolean()).toSet.size == 1 ||
      diag1.map(_.getHollowBoolean()).toSet.size == 1 ||
      diag1.map(_.getSizeBoolean()).toSet.size == 1
    ) {
      return true;
    }

    if (
      diag2.map(_.getColorBoolean()).toSet.size == 1 ||
      diag2.map(_.getShapeBoolean()).toSet.size == 1 ||
      diag2.map(_.getHollowBoolean()).toSet.size == 1 ||
      diag2.map(_.getSizeBoolean()).toSet.size == 1
    ) {
      return true;
    }

    return false;

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
