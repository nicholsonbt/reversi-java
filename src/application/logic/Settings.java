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
	private final static boolean DEFAULT_TWO_PLAYER = true;

	// Declare properties:
	public static BooleanProperty lightModeProperty, boardEnabledProperty;
	
	// Declare values:
	private static int rows, cols;
	private static boolean darkFirst;
	private static double squareLength;
	private static boolean twoPlayer;
	
	private static Pane root;
	private static Game game;
	
	// Initialisation:
	public static void Initialise(Pane newRoot) {
		root = newRoot;
		rows = DEFAULT_ROWS;
		cols = DEFAULT_COLS;
		darkFirst = DEFAULT_DARK_FIRST;
		squareLength = DEFAULT_SQUARE_LENGTH;
		twoPlayer = DEFAULT_TWO_PLAYER;
		
		SetupLightMode();
		boardEnabledProperty = new SimpleBooleanProperty();
		boardEnabledProperty.set(true);
	}
	
	public static void setTwoPlayer() {
		twoPlayer = true;
	}
	
	public static void setOnePlayer() {
		twoPlayer = false;
	}
	
	public static boolean checkTwoPlayer() {
		return twoPlayer;
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
	
	public static void SetupGame(Game gameGUI) {
		game = gameGUI;
		GameLogic.setup();
		
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
		GameLogic.tryPlace(new Vector(row, col));
	}
	
	public static void ColourSquare(Vector pos, SquareColour colour, double opacity) {
		game.board.ColourSquare(pos.x, pos.y, colour, opacity);
	}
	
	public static void RefreshBoard() {
		game.board.RefreshBoard();
	}
	
	public static void AddTranscript(SquareColour colour, Vector pos) {
		game.transcript.AddMove(colour, pos.x, pos.y);
	}
	
	public static void boardEnabled(boolean enabled) {
		boardEnabledProperty.set(enabled);
	}
}




















