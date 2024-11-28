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

def translatePosition(s: String): (Int, Int) = {
  val col = s(0).toUpper match {
    case 'A' => 0
    case 'B' => 1
    case 'C' => 2
    case 'D' => 3
    case _   => -1
  }

  val idx = s(1) match {
    case '1' => 3
    case '2' => 2
    case '3' => 1
    case '4' => 0
    case _   => -1
  }
  (idx, col)
}

def playerMove(
    board: Board,
    player: Array[Piece],
    playerNum: Int
): Array[Piece] = {

  // just a placeholder because the while loop will overwrite it anyways before we filter it out
  var piece = Piece(true, true, true, true)

  var placed = false
  while (!placed) {
    println(s"Player $playerNum, place a piece")
    var input = ""

    while (!input.matches("[A-Da-d][1-4] [0-6]$")) {
      input = scala.io.StdIn.readLine()
      if (!input.matches("^[A-Da-d][1-4] [0-6]$")) {
        println("invalid input")
      }
    }

    var (idx, col) = translatePosition(input.split(" ")(0))

    piece = player(input.substring(2).trim().toInt)
    placed = board.placePiece(piece, idx, col)
  }

  val _player = player.filter(_ != piece)
  return _player
}

def checkWin(board: Board, idx: Int, col: Boolean): Boolean = {
  if (idx < 0 || idx > 3) {
    return false
  }

  val arr = col match {
    case true => board.rotate90().getBoard()(idx).filter(_.isDefined).map(_.get)
    case false => board.getBoard()(idx).filter(_.isDefined).map(_.get)
  }

  if (arr.length < 4) {
    return false
  }

  return arr.map(_.getColorBoolean()).toSet.size == 1 ||
    arr.map(_.getShapeBoolean()).toSet.size == 1 ||
    arr.map(_.getHollowBoolean()).toSet.size == 1 ||
    arr.map(_.getSizeBoolean()).toSet.size == 1
}

def checkDiagWin(board: Board): Boolean = {
  val diag1 = Array(
    board.getBoard()(0)(0),
    board.getBoard()(1)(1),
    board.getBoard()(2)(2),
    board.getBoard()(3)(3)
  ).filter(_.isDefined).map(_.get)

  val diag2 = Array(
    board.getBoard()(0)(3),
    board.getBoard()(1)(2),
    board.getBoard()(2)(1),
    board.getBoard()(3)(0)
  ).filter(_.isDefined).map(_.get)

  if (diag1.length < 4 && diag2.length < 4) {
    return false
  }

  return diag1.map(_.getColorBoolean()).toSet.size == 1 ||
    diag1.map(_.getShapeBoolean()).toSet.size == 1 ||
    diag1.map(_.getHollowBoolean()).toSet.size == 1 |
    diag1.map(_.getSizeBoolean()).toSet.size == 1 ||
    diag2.map(_.getColorBoolean()).toSet.size == 1 ||
    diag2.map(_.getShapeBoolean()).toSet.size == 1 ||
    diag2.map(_.getHollowBoolean()).toSet.size == 1 ||
    diag2.map(_.getSizeBoolean()).toSet.size == 1
}

def start(): Unit = {
  // create a new board
  var board = Board()
  var player1 = initPlayer(true)
  var player2 = initPlayer(false)

  println("""Welcome to Quatro!
    |Player 1 is Red and Player 2 is Blue
    |Player 1 goes first
    |To place a piece, type the position (e.g. A1) and the piece number (e.g. 0)
    |To win, get 4 pieces in a row that share a common attribute""".stripMargin)

  var gameover = false
  var turn = false
  while (!gameover) {
    // print the players
    println(board)
    printPlayer(player1)
    printPlayer(player2)

    turn match {
      case false => player1 = playerMove(board, player1, 1)
      case true  => player2 = playerMove(board, player2, 2)
    }

    clearScreen()

    for (i <- 0 to 3 if !gameover) {
      gameover = checkWin(board, i, false) | checkWin(
        board,
        i,
        true
      ) | (i == 1 && checkDiagWin(board))
    }

    if (gameover) {
      println(board)
      printPlayer(player1)
      printPlayer(player2)
      println("Player " + (if (turn) 2 else 1) + " wins!")
    }

    // in case of tie
    if (player1.length == 0 && player2.length == 0) {
      gameover = true
      println("It's a tie!")
    }

    // switch turns
    turn = !turn
  }
}
