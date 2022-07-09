package application.logic;

public class Board {
	private static Square[][] board;
	private static boolean darkTurn;
	
	public static void Setup() {
		int rows, cols;
		
		rows = Settings.GetRows();
		cols = Settings.GetCols();
		
		board = new Square[rows][cols];
		darkTurn = Settings.GetDarkFirst();
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				board[i][j] = new Square();
			}
		}
		
		
		SetSquare(rows / 2 - 1, cols / 2 - 1, Colour.WHITE);
		SetSquare(rows / 2 - 1, cols / 2, Colour.BLACK);
		SetSquare(rows / 2, cols / 2 - 1, Colour.BLACK);
		SetSquare(rows / 2, cols / 2, Colour.WHITE);
	}
	
	/**
	 * Gets the colour of the current player (BLACK or WHITE).
	 * 
	 * @return Colour.BLACK or Colour.WHITE
	 */
	public static Colour GetPlayer() {
		// If it's the turn of the dark player, return BLACK.
		if (darkTurn)
			return Colour.BLACK;
		
		// Else return WHITE.
		return Colour.WHITE;
	}
	
	/**
	 * Attempts to place a piece (of the colour of the current player)
	 * at the coordinates (row, col). If it succeeds, a piece will be
	 * placed there and all pieces between it and pieces of the same
	 * colour will be flipped.
	 * 
	 * @param row The column at which to place the piece.
	 * 
	 * @param col The row at which to place the piece.
	 */
	public static void PlacePiece(int row, int col) {
		// Get the colour of the current player.
		Colour player = GetPlayer();
		
		// If the location of the piece to place isn't out of bounds,
		// and it can be placed (according to the rules of Reversi),
		// place the piece and set the turn to the next player.
		if (!SquareOutOfBounds(row, col)) {
			if (CanPlace(row, col, player)) {
				Place(row, col, player);
				darkTurn = !darkTurn;
			}
		}
	}
	
	/**
	 * Check if there is any position on the board such that the given
	 * colour can place their piece.
	 * 
	 * @param colour The colour to check for.
	 * 
	 * @return Whether or not the given colour can place a piece on
	 *         the board.
	 */
	private static boolean CanPlace(Colour colour) {
		// For each row and column, if a piece can be placed on the
		// square represented by these, return true.
		for (int row = 0; row < Settings.GetRows(); row++) {
			for (int col = 0; col < Settings.GetCols(); col++) {
				if (CanPlace(row, col, colour)) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * Attempts to place a piece of the given colour on the square
	 * represented by the given row and column.
	 * 
	 * @param row The row to attempt to place a piece on.
	 * 
	 * @param col The column to attempt to place a piece on.
	 * 
	 * @param colour The colour of the piece we are attempting to
	 *               place.
	 * 
	 * @return Whether or not the operation  was successful or not.
	 */
	private static boolean Place(int row, int col, Colour colour) {
		// Set the success flag to false.
		boolean flag = false;
		
		// For each direction to check, check if we can flip in said
		// direction. If we can, perform the flip and set the success
		// flag to true.
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (CanPlace(row, col, colour, i, j) != 0) {
					Flip(row, col, colour, i, j);
					flag = true;
				}
			}
		}
		
		// If the success flag is true, place the piece.
		if (flag == true)
			SetSquare(row, col, colour);
		
		return flag;
	}
	
	/**
	 * 
	 * @param row
	 * @param col
	 * @param colour
	 * @param stepI
	 * @param stepJ
	 */
	private static void Flip(int row, int col, Colour colour, int stepI, int stepJ) {
		int i = stepI;
		int j = stepJ;
		Colour canFlip;
		
		if (colour == Colour.BLACK)
			canFlip = Colour.WHITE;
		else
			canFlip = Colour.BLACK;
		
		
		while (!SquareOutOfBounds(row + i, col + j) && GetColour(row + i, col + j) == canFlip) {
			SetSquare(row + i, col + j, colour);
			i += stepI;
			j += stepJ;
		}
	}
	
	private static int CanPlace(int row, int col, Colour colour, int stepI, int stepJ) {
		int i = stepI;
		int j = stepJ;
		int k = 0;
		Colour canFlip;
		
		if (colour == Colour.BLACK)
			canFlip = Colour.WHITE;
		else
			canFlip = Colour.BLACK;
		
		
		while (!SquareOutOfBounds(row + i, col + j) && GetColour(row + i, col + j) == canFlip) {
			i += stepI;
			j += stepJ;
			k += 1;
		}
		
		if (SquareOutOfBounds(row + i, col + j) || GetColour(row + i, col + j) != colour)
			return 0;
		
		return k;
	}
	
	private static boolean CheckGameOver() {
		if (!CanPlace(Colour.BLACK) && !CanPlace(Colour.WHITE))
			return true;
		
		return false;
	}
	
	
	
	private static boolean CanPlace(int row, int col, Colour colour) {
		if (SquareOutOfBounds(row, col) || GetColour(row, col) != null)
			return false;
		
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (CanPlace(row, col, colour, i, j) != 0) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	
	private static void SetSquare(int row, int col, Colour colour) {
		board[row][col].SetColour(colour);
		Settings.ColourSquare(row, col, colour);
	}
	
	private static Colour GetColour(int row, int col) {
		return board[row][col].GetColour();
	}
	
	
	
	public static boolean EmptySquare(int row, int col) {
		return !SquareOutOfBounds(row, col) && board[row][col].IsEmpty();
	}
	
	public static boolean SquareOutOfBounds(int row, int col) {
		// The given row is less than zero, so out of bounds.
		if (row < 0)
			return true;
		
		// The given column is less than zero, so out of bounds.
		else if (col < 0)
			return true;
		
		// The given row is greater or equal to the number of rows,
		// so out of bounds.
		else if (row >= Settings.GetRows())
			return true;
		
		// The given column is greater or equal to the number of
		// columns, so out of bounds.
		else if (col >= Settings.GetCols())
			return true;
		
		// The row and column are within the bounds of the board.
		return false;
	}
	
}
