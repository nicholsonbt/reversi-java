package application.logic;

public class Settings {
	// Initialise default values:
	private final static int DEFAULT_ROWS = 8;
	private final static int DEFAULT_COLS = 8;
	private final static boolean DEFAULT_DARK_FIRST = true;
	private final static double DEFAULT_SQUARE_LENGTH = 40;
	
	// Declare values:
	private static int rows, cols;
	private static boolean darkFirst;
	private static double squareLength;
	
	private static application.gui.Board board;
	
	// Initialise values with default values:
	public static void Initialise() {
		rows = DEFAULT_ROWS;
		cols = DEFAULT_COLS;
		darkFirst = DEFAULT_DARK_FIRST;
		squareLength = DEFAULT_SQUARE_LENGTH;
	}
	
	public static void Setup(application.gui.Board guiBoard) {
		board = guiBoard;
		Board.Setup();
	}
	
	
	// Getters:
	public static int GetRows() {
		return rows;
	}
	
	public static int GetCols() {
		return cols;
	}
	
	public static boolean GetDarkFirst() {
		return darkFirst;
	}
	
	public static double GetSquareLength() {
		return squareLength;
	}
	
	
	// Setters:
	public static void SetRows(int newRows) {
		rows = newRows;
	}
	
	public static void SetCols(int newCols) {
		cols = newCols;
	}
	
	public static void SetDarkFirst(boolean newDarkFirst) {
		darkFirst = newDarkFirst;
	}
	
	public static void SetSquareLength(double newSquareLength) {
		squareLength = newSquareLength;
	}
	
	public static void PlacePiece(int row, int col) {
		application.logic.Board.PlacePiece(row, col);
	}
	
	public static void ColourSquare(int row, int col, Colour colour) {
		board.ColourSquare(row, col, colour);
	}
}




















