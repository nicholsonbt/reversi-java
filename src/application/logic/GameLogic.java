package application.logic;

import java.util.List;

public class GameLogic {
	private static BoardLogic board;
	private static boolean darkTurn;
	
	
	public static void setup() {
		board = new BoardLogic();
		darkTurn = Settings.GetDarkFirst();

		play();
	}
	
	private static void nextTurn() {
		darkTurn = !darkTurn;
		
		if (!Move.canPlace(board, getPlayer())) {
			darkTurn = !darkTurn;
		
			if (!Move.canPlace(board, getPlayer())) {
				System.out.println("Game Over!");
				return;
			}
		}
		
		play();
			
	}
	
	private static void play() {
		if (Settings.checkTwoPlayer() || darkTurn) {
			Settings.boardEnabled(true);
			showPossible();
		}
		
		else {
			Settings.boardEnabled(false);
			tryPlace(ReversiAI.calculateOptimalMove(board.copy(), getPlayer()));
		}
	}
	

	/**
	 * Gets the colour of the current player (BLACK or WHITE).
	 * 
	 * @return Colour.BLACK or Colour.WHITE
	 */
	public static SquareColour getPlayer() {
		// If it's the turn of the dark player, return BLACK.
		if (darkTurn)
			return SquareColour.BLACK;
		
		// Else return WHITE.
		return SquareColour.WHITE;
	}
	
	public static void tryPlace(Vector pos) {
		SquareColour player = getPlayer();
		
		if (Move.canPlace(board, player, pos)) {
			board.place(player, pos);
			Settings.AddTranscript(player, pos);
			
			nextTurn();
		}
	}
	
	private static void showPossible() {
		SquareColour player = getPlayer();
		
		List<Vector> moves = Move.possibleMoves(board, player);
		
		refreshBoard();
		
		for (Vector move : moves) {
			Settings.ColourSquare(move, player, 0.25);
		}
	}
	
	private static void refreshBoard() {
		Settings.RefreshBoard();
	}
}
