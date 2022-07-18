package application.logic;

import java.util.ArrayList;
import java.util.List;

public class Move {
	public static boolean canPlace(BoardLogic board) {
		if (canPlace(board, SquareColour.BLACK) || canPlace(board, SquareColour.WHITE))
			return true;
		
		return false;
	}
	
	public static SquareColour notPlayer(SquareColour player) {
		if (player == SquareColour.BLACK)
			return SquareColour.WHITE;
		
		return SquareColour.BLACK;
	}
	
	public static SquareColour nextPlayer(BoardLogic board, SquareColour lastPlayer) {
		SquareColour otherPlayer = notPlayer(lastPlayer);
		
		if (canPlace(board, otherPlayer))
			return otherPlayer;
		
		else if (canPlace(board, lastPlayer))
			return lastPlayer;
		
		else
			return null;
	}
	
	public static boolean canPlace(BoardLogic board, SquareColour player) {
		if (possibleMoves(board, player).size() == 0)
			return false;
		
		return true;
	}
	
	public static List<Vector> possibleMoves(BoardLogic board, SquareColour player) {
		List<Vector> positions = new ArrayList<Vector>();
		Vector pos;
		
		for (int row = 0; row < Settings.GetRows(); row++) {
			for (int col = 0; col < Settings.GetCols(); col++) {
				pos = new Vector(row, col);
				
				if (canPlace(board, player, pos)) {
					positions.add(pos);
				}
			}
		}
		
		return positions;
	}
	

	public static boolean canPlace(BoardLogic board, SquareColour player, Vector pos) {
		// Pieces can't be placed on squares that are out of bounds or not empty.
		if (!checkInBounds(board, pos) || !checkEmpty(board, pos))
			return false;
		
		for (int dirRow = -1; dirRow <= 1; dirRow++) {
			for (int dirCol = -1; dirCol <= 1; dirCol++) {
				if (canPlace(board.copy(), player, pos, new Vector(dirRow, dirCol))) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public static boolean canPlace(BoardLogic board, SquareColour player, Vector pos, Vector dir) {
		Vector newPos = Vector.Add(pos, dir);
		
		int k = 0;
		SquareColour canFlip;
		
		if (player == SquareColour.BLACK)
			canFlip = SquareColour.WHITE;
		else
			canFlip = SquareColour.BLACK;
		
		
		while (checkInBounds(board, newPos) && board.getColour(newPos) == canFlip) {
			newPos = Vector.Add(newPos, dir);
			k += 1;
		}
		
		if (k == 0 || !checkInBounds(board, newPos) || board.getColour(newPos) != player)
			return false;
		
		return true;
	}
	
	
	public static boolean checkEmpty(BoardLogic board, Vector pos) {
		return board.getColour(pos) == null;
	}

	public static boolean checkInBounds(BoardLogic board, Vector pos) {
		// The given row is less than zero, so out of bounds.
		if (pos.x < 0)
			return false;
				
		// The given column is less than zero, so out of bounds.
		else if (pos.y < 0)
			return false;
				
		// The given row is greater or equal to the number of rows,
		// so out of bounds.
		else if (pos.x >= Settings.GetRows())
			return false;
				
		// The given column is greater or equal to the number of
		// columns, so out of bounds.
		else if (pos.y >= Settings.GetCols())
			return false;
				
		// The row and column are within the bounds of the board.
		return true;
	}
}
