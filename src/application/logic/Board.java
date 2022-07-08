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
	
	public static Colour GetPlayer() {
		if (darkTurn)
			return Colour.BLACK;
		
		return Colour.WHITE;
	}
	
	public static boolean PlacePiece(int row, int col) {
		Colour player = GetPlayer();
		
		if (!CanPlace(row, col, player))
			return false;
		
		SetSquare(row, col, player);
		darkTurn = !darkTurn;
		return true;
	}
	
	private static boolean CanPlace(int row, int col, Colour colour) {
		if (SquareOutOfBounds(row, col))
			return false;
		return true;
	}
	
	private static void SetSquare(int row, int col, Colour colour) {
		board[row][col].SetColour(colour);
		Settings.ColourSquare(row, col, colour);
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
