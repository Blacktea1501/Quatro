package Piece

// constructor
class Piece(
    val color: Boolean,
    val size: Boolean,
    val shape: Boolean,
    val hollow: Boolean
):

  private val _color: Boolean = color
  private val _size: Boolean = size
  private val _shape: Boolean = shape
  private val _hollow: Boolean = hollow

  // getters
  def getColorBoolean(): Boolean = _color
  def getSizeBoolean(): Boolean = _size
  def getShapeBoolean(): Boolean = _shape
  def getHollowBoolean(): Boolean = _hollow

  override def toString(): String = {
    // if color is true, then red, else blue
    val color = if this.getColorBoolean() then "\u001b[31m" else "\u001b[34m"
    val size = if this.getSizeBoolean() then "2" else "1"
    val (shape_open, shape_close) =
      if this.getShapeBoolean() then ("(", ")") else ("[", "]")
    val piece =
      if this.getHollowBoolean() then
        shape_open + shape_open + size + shape_close + shape_close
      else " " + shape_open + size + shape_close + " "
    // reset color afterwards
    return color + piece + "\u001b[0m"
  }
