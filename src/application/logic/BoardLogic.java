package application.logic;

public class BoardLogic {
	private SquareLogic[][] board;
	
	public BoardLogic() {
		int rows, cols;
		
		rows = Settings.GetRows();
		cols = Settings.GetCols();
		
		board = new SquareLogic[rows][cols];
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				board[i][j] = new SquareLogic();
			}
		}
		
		
		setSquare(SquareColour.WHITE, new Vector(rows / 2 - 1, cols / 2 - 1));
		setSquare(SquareColour.BLACK, new Vector(rows / 2 - 1, cols / 2));
		setSquare(SquareColour.BLACK, new Vector(rows / 2, cols / 2 - 1));
		setSquare(SquareColour.WHITE, new Vector(rows / 2, cols / 2));
	}
	
	public BoardLogic(SquareLogic[][] squares) {
		board = squares;
	}
	
	/**
	 * Flips the piece in the given direction and all pieces
	 * neighbouring a newly flipped piece in said direction if the
	 * piece is not the colour of the current player.
	 * 
	 * @param row The row of the placed piece.
	 * 
	 * @param col The column of the placed piece.
	 * 
	 * @param colour The colour of the placed piece.
	 * 
	 * @param stepI The Y value for the Cartesian direction vector
	 *              representing the direction to flip in.
	 *              
	 * @param stepJ The X value for the Cartesian direction vector
	 *              representing the direction to flip in.
	 */
	public void flip(SquareColour colour, Vector pos, Vector dir) {
		Vector newPos = Vector.Add(pos, dir);
		SquareColour canFlip;
		
		if (colour == SquareColour.BLACK)
			canFlip = SquareColour.WHITE;
		else
			canFlip = SquareColour.BLACK;
		
		
		while (Move.checkInBounds(this, newPos) && getColour(newPos) == canFlip) {
			setSquare(colour, newPos);
			newPos = Vector.Add(newPos, dir);
		}
	}
	
	public void testFlip(SquareColour colour, Vector pos, Vector dir) {
		Vector newPos = Vector.Add(pos, dir);
		SquareColour canFlip;
		
		if (colour == SquareColour.BLACK)
			canFlip = SquareColour.WHITE;
		else
			canFlip = SquareColour.BLACK;
		
		
		while (Move.checkInBounds(this, newPos) && getColour(newPos) == canFlip) {
			testSetSquare(colour, newPos);
			newPos = Vector.Add(newPos, dir);
		}
	}
	
	
	public void place(SquareColour player, Vector pos) {
		Vector dir;
		
		setSquare(player, pos);
		
		for (int dirRow = -1; dirRow <= 1; dirRow++) {
			for (int dirCol = -1; dirCol <= 1; dirCol++) {
				dir = new Vector(dirRow, dirCol);
				
				if (Move.canPlace(this.copy(), player, pos, dir)) {
					flip(player, pos, dir);
				}
			}
		}
	}
	
	public void testPlace(SquareColour player, Vector pos) {
		Vector dir;
		
		testSetSquare(player, pos);
		
		for (int dirRow = -1; dirRow <= 1; dirRow++) {
			for (int dirCol = -1; dirCol <= 1; dirCol++) {
				dir = new Vector(dirRow, dirCol);
				
				if (Move.canPlace(this.copy(), player, pos, dir)) {
					testFlip(player, pos, dir);
				}
			}
		}
	}
	
	public void printBoard() {
		System.out.println();
		SquareColour c;
		
		for (int i = 0; i < Settings.GetRows(); i++) {
			for (int j = 0; j < Settings.GetCols(); j++) {
				c = board[i][j].GetColour();
				
				if (c == SquareColour.BLACK)
					System.out.print("B");
				else if (c == SquareColour.WHITE)
					System.out.print("W");
				else
					System.out.print(" ");
			}
			
			System.out.println();
		}
	}
	
	
	/**
	 * Sets the colour of the square at the given coordinates.
	 * 
	 * @param row The row component of the squares coordinates.
	 * 
	 * @param col The column component of the squares coordinates.
	 * 
	 * @param colour The new colour of the square.
	 */
	public void setSquare(SquareColour colour, Vector pos) {
		getSquare(pos).SetColour(colour);
		Settings.ColourSquare(pos, colour, 1);
	}
	
	public void testSetSquare(SquareColour colour, Vector pos) {
		getSquare(pos).SetColour(colour);
	}
	
	public SquareLogic getSquare(Vector pos) {
		return board[pos.x][pos.y];
	}
	
	/**
	 * Gets the colour of the square at the given coordinates.
	 * 
	 * @param row The row component of the coordinates of the square.
	 * 
	 * @param col The column component of the squares coordinates.
	 * 
	 * @return The colour of the square at the given coordinates.
	 */
	public SquareColour getColour(Vector pos) {
		return getSquare(pos).GetColour();
	}
	
	public BoardLogic copy() {
		return new BoardLogic(copySquares());
	}
	
	private SquareLogic[][] copySquares() {
		SquareLogic[][] squares = new SquareLogic[Settings.GetRows()][Settings.GetCols()];
		
		for (int row = 0; row < Settings.GetRows(); row++) {
			for (int col = 0; col < Settings.GetCols(); col++) {
				squares[row][col] = board[row][col].copy();
			}
		}
		
		return squares;
	}
}
