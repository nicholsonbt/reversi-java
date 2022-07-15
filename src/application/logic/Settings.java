package application.logic;

import application.gui.Game;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.layout.Pane;

public class Settings {
	// Initialise default values:
	public final static double DEFAULT_WIDTH = 1080;
	public final static double DEFAULT_WIDTH_HEIGHT_RATIO = 1.5;
	
	private final static int DEFAULT_ROWS = 8;
	private final static int DEFAULT_COLS = 8;
	private final static boolean DEFAULT_DARK_FIRST = true;
	private final static double DEFAULT_SQUARE_LENGTH = 40;
	private final static boolean DEFAULT_LIGHT_MODE = true;

	// Declare properties:
	public static BooleanProperty lightModeProperty;
	
	// Declare values:
	private static int rows, cols;
	private static boolean darkFirst;
	private static double squareLength;
	
	private static Pane root;
	private static Game game;
	
	// Initialisation:
	public static void Initialise(Pane newRoot) {
		root = newRoot;
		rows = DEFAULT_ROWS;
		cols = DEFAULT_COLS;
		darkFirst = DEFAULT_DARK_FIRST;
		squareLength = DEFAULT_SQUARE_LENGTH;
		SetupLightMode();
	}
	
	private static void SetupLightMode() {
		lightModeProperty = new SimpleBooleanProperty();
		lightModeProperty.addListener((observable, oldValue, newValue) -> {
            if (newValue)
            	SetModeLight();
            else
            	SetModeDark();
        });
		
		if (DEFAULT_LIGHT_MODE)
			SetLightMode();
		else
			SetDarkMode();
	}
	
	private static void SetModeLight() {
		root.getStyleClass().removeAll("dark");
		root.getStyleClass().add("light");
	}
	
	private static void SetModeDark() {
		root.getStyleClass().removeAll("light");
		root.getStyleClass().add("dark");
	}
	
	public static void Setup(Game gameGUI) {
		game = gameGUI;
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
	
	public static boolean GetLightMode() {
		return lightModeProperty.get();
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
	
	public static void SetDarkMode() {
		lightModeProperty.set(false);
	}
	
	public static void SetLightMode() {
		lightModeProperty.set(true);
	}
	
	public static void ToggleLightDark() {
		if (GetLightMode())
			SetDarkMode();
		else
			SetLightMode();
	}
	
	
	
	// Logic:
	public static void PlacePiece(int row, int col) {
		application.logic.Board.PlacePiece(row, col);
	}
	
	public static void ColourSquare(int row, int col, Colour colour, double opacity) {
		game.board.ColourSquare(row, col, colour, opacity);
	}
	
	public static void RefreshBoard() {
		game.board.RefreshBoard();
	}
	
	public static void AddTranscript(Colour colour, int row, int col) {
		game.transcript.AddMove(colour, row, col);
	}
}




















