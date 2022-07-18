package application.logic;

import java.util.List;

public class ReversiAI {
	public static Vector calculateOptimalMove(BoardLogic board, SquareColour colour) {
		List<Vector> possibleMoves = Move.possibleMoves(board, colour);
		
		return calculateOptimalMove(board.copy(), colour, colour, possibleMoves, 3).item0;
	}
	
	
	private static Tuple<Vector, Double> calculateOptimalMove(BoardLogic board, SquareColour desired, SquareColour colour, List<Vector> possible, int i) {
		Vector bestPosition = new Vector();
		List<Vector> newPossible;
		BoardLogic newBoard;
		SquareColour newColour;
		double score, bestScore;
		
		i -= 1;
		
		boolean flag = true;
		bestScore = 0;
		
		
		for (Vector pos : possible) {
			newBoard = board.copy();
			newBoard.testPlace(colour, pos);
			
			newColour = Move.nextPlayer(newBoard, colour);
			newPossible = Move.possibleMoves(newBoard, newColour);
			
			if (i <= 0)
				score = scoreBoard(newBoard);
			
			else
				score = calculateOptimalMove(newBoard, desired, newColour, newPossible, i).item1;
			
			if (flag || (colour == desired && bestScore < score) || (colour == Move.notPlayer(desired) && bestScore > score)) {
				bestPosition = pos;
				bestScore = score;
				flag = false;
			}
		}
		
		return new Tuple<Vector, Double>(bestPosition, bestScore);
	}
	
	
	public static double scoreBoard(BoardLogic board) {
		// +1 = black has won.
		// -1 = white has won.
		return betterScore(board);
	}
	
	private static double basicScore(BoardLogic board) {
		int black, white;
		
		black = 0;
		white = 0;
		
		for (int row = 0; row < Settings.GetRows(); row++) {
			for (int col = 0; col < Settings.GetCols(); col++) {
				SquareColour colour = board.getColour(new Vector(row, col));
				
				if (colour == SquareColour.BLACK)
					black += 1;
				
				else if (colour == SquareColour.WHITE)
					white += 1;
			}
		}
		
		return 2 * (black / (black + white)) - 1;
	}
	
	private static double betterScore(BoardLogic board) {
		int black, white, val;
		black = 0;
		white = 0;
		
		int[][] weights = {
				{ 4, -3,  2,  2,  2,  2, -3,  4},
				{-3, -4, -1, -1, -1, -1, -4, -3},
				{ 2, -1,  1,  0,  0,  1, -1,  2},
				{ 2, -1,  0,  1,  1,  0, -1,  2},
				{ 2, -1,  0,  1,  1,  0, -1,  2},
				{ 2, -1,  1,  0,  0,  1, -1,  2},
				{-3, -4, -1, -1, -1, -1, -4, -3},
				{ 4, -3,  2,  2,  2,  2, -3,  4}
		};
		
		for (int row = 0; row < Settings.GetRows(); row++) {
			for (int col = 0; col < Settings.GetCols(); col++) {
				SquareColour colour = board.getColour(new Vector(row, col));
				
				val = weights[row][col];
				
				
				if (colour == SquareColour.BLACK)
					black += val;
				
				else if (colour == SquareColour.WHITE)
					white += val;
			}
		}
		
		return black - white;
	}
}
