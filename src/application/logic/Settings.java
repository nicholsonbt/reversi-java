package application.logic;

import application.gui.Game;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.layout.Background;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class Settings {
	// Initialise default values:
	private final static int DEFAULT_ROWS = 8;
	private final static int DEFAULT_COLS = 8;
	private final static boolean DEFAULT_DARK_FIRST = true;
	private final static double DEFAULT_SQUARE_LENGTH = 40;
	private final static String DEFAULT_LIGHT_BACKGROUND = "e6e9eb";
	private final static String DEFAULT_DARK_BACKGROUND = "08152b";
	private final static String DEFAULT_LIGHT_BACKGROUND_HIGHLIGHT = "59acff";
	private final static String DEFAULT_DARK_BACKGROUND_HIGHLIGHT = "59acff";
	private final static String DEFAULT_LIGHT_TEXT = "000000";
	private final static String DEFAULT_DARK_TEXT = "ffffff";
	private final static String DEFAULT_LIGHT_TEXT_HIGHLIGHT = "ffffff";
	private final static String DEFAULT_DARK_TEXT_HIGHLIGHT = "000000";
	private final static double DEFAULT_SCALE = 100;
	private final static boolean DEFAULT_LIGHT_MODE = true;

	// Declare properties:
	public static BooleanProperty lightModeProperty;
	public static DoubleProperty scaleProperty;
	
	// Declare bindings:
	public static ObjectBinding<Background> backgroundColourBinding, highlightBackgroundColourBinding;
	public static ObjectBinding<Paint> highlightColourBinding, textColourBinding, highlightTextColourBinding;

	
	// Declare values:
	private static int rows, cols;
	private static boolean darkFirst;
	private static double squareLength;
	
	private static Game game;
	
	// Initialisation:
	public static void Initialise() {
		rows = DEFAULT_ROWS;
		cols = DEFAULT_COLS;
		darkFirst = DEFAULT_DARK_FIRST;
		squareLength = DEFAULT_SQUARE_LENGTH;
		
		lightModeProperty = new SimpleBooleanProperty();
		scaleProperty = new SimpleDoubleProperty(DEFAULT_SCALE);
		
		if (DEFAULT_LIGHT_MODE)
			SetLightMode();
		else
			SetDarkMode();
		
		CreateBindings();
	}
	
	private static void CreateBindings() {
		backgroundColourBinding = Bindings.createObjectBinding(() -> {
			return GetBackgroundColour();
		}, lightModeProperty);
		
		highlightBackgroundColourBinding = Bindings.createObjectBinding(() -> {
			return GetHighlightBackgroundColour();
		}, lightModeProperty);
		
		highlightColourBinding = Bindings.createObjectBinding(() -> {
			return GetHighlightColour();
		}, lightModeProperty);
		
		textColourBinding = Bindings.createObjectBinding(() -> {
			return GetTextColour();
		}, lightModeProperty);
		
		highlightTextColourBinding = Bindings.createObjectBinding(() -> {
			return GetHighlightTextColour();
		}, lightModeProperty);
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
	
	public static Background GetBackgroundColour() {
		String colour;
		
		if (GetLightMode())
			colour = DEFAULT_LIGHT_BACKGROUND;
		else
			colour = DEFAULT_DARK_BACKGROUND;
		
		return Background.fill(Paint.valueOf(colour));
	}
	
	public static Background GetHighlightBackgroundColour() {
		return Background.fill(GetHighlightColour());
	}
	
	public static Paint GetHighlightColour() {
		String colour;
		
		if (GetLightMode())
			colour = DEFAULT_LIGHT_BACKGROUND_HIGHLIGHT;
		else
			colour = DEFAULT_DARK_BACKGROUND_HIGHLIGHT;
		
		return Paint.valueOf(colour);
	}
	
	public static Paint GetTextColour() {
		String colour;
		
		if (GetLightMode())
			colour = DEFAULT_LIGHT_TEXT;
		else
			colour = DEFAULT_DARK_TEXT;
		
		return Paint.valueOf(colour);
	}
	
	public static Paint GetHighlightTextColour() {
		String colour;
		
		if (GetLightMode())
			colour = DEFAULT_LIGHT_TEXT_HIGHLIGHT;
		else
			colour = DEFAULT_DARK_TEXT_HIGHLIGHT;
		
		return Paint.valueOf(colour);
	}
	
	public static double GetScale() {
		return scaleProperty.get();
	}
	
	public static double GetBaseScale() {
		return DEFAULT_SCALE;
	}
	
	public static double GetScalePerc() {
		return GetScale() / GetBaseScale();
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
	
	public static void SetScale(double newScale) {
		scaleProperty.set(newScale);
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
	
	
	// GUI:
	public static ObjectBinding<Font> GetFontBinding(double size) {
		return Bindings.createObjectBinding(() -> {
			return new Font(size * GetScalePerc());
		}, scaleProperty);
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




















